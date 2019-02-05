package com.d0tplist;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SwingRun extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
            Document document = editor.getDocument();
            VirtualFile file = FileDocumentManager.getInstance().getFile(editor.getDocument());

            if (file == null) {
                return;
            }

            if (!file.getName().endsWith(".java")) {
                return;
            }

            if (document.getText().isEmpty()) {
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
            throws MalformedURLException, IllegalAccessException, InstantiationException {
        URL classUrl = javaClass.getParent().toFile().toURI().toURL();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classUrl});
        try {
            Class<?> clazz = Class.forName(name, true, classLoader);
            Object o = clazz.newInstance();

            if (o instanceof Component) {
                load((Component) o, event);
            }
        } catch (ClassNotFoundException ex) {
            PlaygroundWindow.instance.load(new JLabel("Unable to load " + name));
        }
    }

    public void doEvil(String source, String name, AnActionEvent event) throws Exception {
        Path javaFile = saveSource(source, name);
        Path classFile = compileSource(javaFile, name);
        runClass(classFile, name, event);
    }

    private void load(Component component, AnActionEvent action) {

        try {
            if (PlaygroundWindow.instance == null) {
                ToolWindowManager.getInstance(action.getProject()).getToolWindow("Swing Playground").show(null);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(action.getRequiredData(CommonDataKeys.EDITOR).getComponent(), "Please open the Swing Playground tool window :0");
            return;
        }

        //Double check why not
        if (PlaygroundWindow.instance == null) {
            return;
        }

        PlaygroundWindow.instance.load(component);
    }
}
