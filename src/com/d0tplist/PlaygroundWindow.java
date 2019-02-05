package com.d0tplist;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlaygroundWindow extends javax.swing.JPanel {


    public static PlaygroundWindow instance;

    /**
     * Creates new form Playground
     */
    public PlaygroundWindow(ToolWindow toolWindow) {
        initComponents();
        instance = PlaygroundWindow.this;

        bBackground.addActionListener(event -> {
            if (invalid()) {
                return;
            }

            getComponent().setBackground(getColor());
            renderColors();

            update();
        });

        bForeground.addActionListener(event -> {
            if (invalid()) {
                return;
            }

            getComponent().setForeground(getColor());
            renderColors();

            update();
        });

        sWidth.addChangeListener(e -> {
            if (invalid()) {
                return;
            }

            getComponent().setPreferredSize(new Dimension(
                    (int) sWidth.getValue(),
                    (int) sHeight.getValue()
            ));

            update();
        });

        sHeight.addChangeListener(e -> {
            if (invalid()) {
                return;
            }

            getComponent().setPreferredSize(new Dimension(
                    (int) sWidth.getValue(),
                    (int) sHeight.getValue()
            ));

            update();
        });

        KeyAdapter keyEvent = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    getComponent().setPreferredSize(new Dimension(
                            (int) sWidth.getValue(),
                            (int) sHeight.getValue()
                    ));

                    update();
                }
            }
        };

        sWidth.addKeyListener(keyEvent);
        sHeight.addKeyListener(keyEvent);

        cbEnabled.addActionListener(event -> {
            if (invalid()) {
                return;
            }

            getComponent().setEnabled(cbEnabled.isSelected());
            update();
        });

        cbOpaque.addActionListener(event -> {
            if (invalid()) {
                return;
            }

            if (getComponent() instanceof JComponent) {
                ((JComponent) getComponent()).setOpaque(cbOpaque.isSelected());
            }
            update();
        });

        container.setLayout(new CenterLayout());
        jPanel1.setBackground((ColorUIResource) UIManager.get("Panel.background"));
        Color foreground = (ColorUIResource) UIManager.get("Label.foreground");

        jLabel1.setForeground(foreground);
        jLabel2.setForeground(foreground);
        cbOpaque.setForeground(foreground);
        cbEnabled.setForeground(foreground);

        bBackground.setForeground(foreground);
        bForeground.setForeground(foreground);

        sHeight.setEnabled(false);
        sWidth.setEnabled(false);
        bForeground.setEnabled(false);
        bBackground.setEnabled(false);
        cbEnabled.setEnabled(false);
        cbOpaque.setEnabled(false);

    }

    private void update() {
        container.revalidate();
        container.repaint();
    }

    private void renderColors() {

        if (invalid()) {
            bBackground.setText("Background");
            bForeground.setText("Foreground");
            return;
        }

        bBackground.setText("Background: #" + Integer.toHexString(getComponent().getBackground().getRGB()).toUpperCase());
        bForeground.setText("Background: #" + Integer.toHexString(getComponent().getForeground().getRGB()).toUpperCase());
    }

    private Component getComponent() {
        return container.getComponent(0);
    }

    private Color getColor() {
        return JColorChooser.showDialog(this, "Select a color", null);
    }

    private boolean invalid() {
        return container.getComponentCount() < 1;
    }


    public final JPanel getContent() {
        return this;
    }

    public final void load(Component component) {

        container.removeAll();

        if (component == null) {
            return;
        }

        cbEnabled.setSelected(component.isEnabled());

        sWidth.setValue(component.getPreferredSize().width);
        sHeight.setValue(component.getPreferredSize().height);


        if (component instanceof JComponent) {
            ((JComponent) component).setOpaque(cbOpaque.isSelected());
            cbOpaque.setEnabled(true);
        } else {
            cbOpaque.setSelected(false);
            cbOpaque.setEnabled(false);
        }

        container.add(component);
        container.revalidate();
        container.repaint();

        renderColors();

        sHeight.setEnabled(true);
        sWidth.setEnabled(true);
        bForeground.setEnabled(true);
        bBackground.setEnabled(true);
        cbEnabled.setEnabled(true);
        cbOpaque.setEnabled(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sWidth = new javax.swing.JSpinner();
        sHeight = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bBackground = new javax.swing.JButton();
        bForeground = new javax.swing.JButton();
        cbOpaque = new javax.swing.JCheckBox();
        cbEnabled = new javax.swing.JCheckBox();
        container = new GridPanel();

        setBackground(new Color(60, 63, 65));

        jPanel1.setBackground(new Color(60, 63, 65));

        sWidth.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        sHeight.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Width:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Height:");

        bBackground.setText("Background: #FFFFFF");

        bForeground.setText("Foreground: #FFFFFF");

        cbOpaque.setForeground(new java.awt.Color(255, 255, 255));
        cbOpaque.setText("Opaque");

        cbEnabled.setForeground(new java.awt.Color(255, 255, 255));
        cbEnabled.setText("Enabled");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bForeground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbOpaque)
                                        .addComponent(cbEnabled))
                                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(bBackground)
                                        .addComponent(sWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(cbOpaque))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(bForeground)
                                        .addComponent(cbEnabled)))
        );

        container.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
                containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
                containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 221, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>


    // Variables declaration - do not modify
    private javax.swing.JButton bBackground;
    private javax.swing.JButton bForeground;
    private javax.swing.JCheckBox cbEnabled;
    private javax.swing.JCheckBox cbOpaque;
    private javax.swing.JPanel container;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner sHeight;
    private javax.swing.JSpinner sWidth;
    // End of variables declaration
}
