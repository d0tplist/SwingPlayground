package com.d0tplist;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SwingRun extends AnAction {


    @Override
    public void update(@NotNull AnActionEvent anActionEvent) {
        final Project project = anActionEvent.getProject();

        if (project == null) {
            anActionEvent.getPresentation().setEnabled(false);
            return;
        }

        final Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);

        if (editor == null) {
            anActionEvent.getPresentation().setEnabled(false);
            return;
        }

        Document document = editor.getDocument();

        VirtualFile file = FileDocumentManager.getInstance().getFile(document);

        if (file == null) {
            anActionEvent.getPresentation().setEnabled(false);
            return;
        }

        anActionEvent.getPresentation().setEnabled(file.getName().endsWith(".java"));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
            Document document = editor.getDocument();
            VirtualFile file = FileDocumentManager.getInstance().getFile(document);

            if (file == null) {
                return;
            }

            if (!file.getName().endsWith(".java")) {
                return;
            }

            if (document.getText().isEmpty()) {
                return;
            }

            PsiFile requiredData = e.getRequiredData(CommonDataKeys.PSI_FILE);

            PsiJavaFile containingFile = (PsiJavaFile) requiredData.getContainingFile();

            /* //Magic defense disabled = true
            if (containingFile.getImportList() != null) {
                for (PsiImportStatementBase importStatement : containingFile.getImportList().getAllImportStatements()) {

                    if (importStatement.getImportReference() == null) {
                        continue;
                    }

                    if (importStatement.getImportReference().getQualifiedName().startsWith("java.lang.reflect")) {
                        JOptionPane.showMessageDialog(editor.getComponent(), "We can't load this insecure component :( ", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            } else {
                return;
            }
            */

            boolean letsgo = false;

            for (PsiClass aClass : containingFile.getClasses()) {
                if (aClass.getSuperClass() == null) {
                    return;
                }
                try {

                    String clazz = aClass.getSuperClass().getQualifiedName();

                    if (clazz == null) {
                        continue;
                    }

                    if (clazz.startsWith("java.awt.") ||
                            clazz.startsWith("javax.swing.")) {
                        letsgo = true;
                    }
                } catch (Exception ignored) {

                }
            }

            if (!letsgo) {
                return;
            }

            String name = file.getName().split("\\.")[0];

            String src = document.getText();

            String[] split = src.split("\n");

            StringBuilder builder = new StringBuilder();

            boolean next = false;

            for (String s : split) {

                if (!next && s.startsWith("package")) {
                    next = true;
                    continue;
                }

                builder.append(s);
                builder.append("\n");
            }

            src = builder.toString();

            try {
                doEvil(src, name, e);
            } catch (Exception e1) {
                TextArea textArea = new TextArea();
                textArea.setEditable(false);
                textArea.setPreferredSize(new Dimension(350, 600));

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e1.printStackTrace(pw);
                String sStackTrace = sw.toString();

                textArea.setText(sStackTrace);

                load(textArea, e);
            }
        } catch (java.lang.AssertionError ignored) {
            //jijijiji
        }
    }

    private Path saveSource(String source, String name) throws IOException {
        String tmpProperty = System.getProperty("java.io.tmpdir");
        Path sourcePath = Paths.get(tmpProperty, name + ".java");
        Files.write(sourcePath, source.getBytes(UTF_8));
        return sourcePath;
    }

    private Path compileSource(Path javaFile, String name) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, javaFile.toFile().getAbsolutePath());
        return javaFile.getParent().resolve(name + ".class");
    }

    private void runClass(Path javaClass, String name, AnActionEvent event)
            throws MalformedURLException {
        URL classUrl = javaClass.getParent().toFile().toURI().toURL();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classUrl});

        try {
            Class<?> clazz = Class.forName(name, true, classLoader);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();

            Constructor constructor = null;

            for (Constructor<?> cons : constructors) {
                if (cons.getParameterCount() == 0) {
                    constructor = cons;

                    if (!cons.isAccessible()) {
                        cons.setAccessible(true);
                    }

                    break;
                }
            }

            if (constructor == null) {
                load(new JLabel("Please add a public constructor() to your class"), event);
                return;
            }

            Object o = constructor.newInstance();

            if (o instanceof Component) {
                load((Component) o, event);
            } else {
                load(new JLabel("The instance is not a component"), event);
            }
        } catch (Exception ex) {
            load(new JLabel("Unable to load " + name), event);
        }
    }

    public void doEvil(String source, String name, AnActionEvent event) throws Exception {
        Path javaFile = saveSource(source, name);
        Path classFile = compileSource(javaFile, name);
        runClass(classFile, name, event);
    }

    private void load(Component component, AnActionEvent action) {

        if (action.getProject() == null) {
            return;
        }

        try {
            if (PlaygroundWindow.instance == null) {
                ToolWindow swing_playground = ToolWindowManager.getInstance(action.getProject()).getToolWindow("Swing Playground");

                swing_playground.show(null);

                ((PlaygroundWindow) swing_playground).load(component);
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(action.getRequiredData(CommonDataKeys.EDITOR).getComponent(), "Please open the Swing Playground tool window :0");
            return;
        }

        PlaygroundWindow.instance.load(component);
    }
}
