package com.d0tplist.cases;


import java.awt.*;

/**
 * @author alex
 */
public class Complex extends javax.swing.JPanel {

    public Complex() {
        initComponents();

        jPanel1.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintChildren(g);
        } else {
            super.paint(g);
        }
    }

    @SuppressWarnings("all")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tfUser = new javax.swing.JTextField();
        luser = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        lpass = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        bCancel = new javax.swing.JButton();
        bEnter = new javax.swing.JButton();
        lLogo = new javax.swing.JLabel();
        lFooter = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[]{0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[]{0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        setLayout(layout);

        tfUser.setPreferredSize(new java.awt.Dimension(10, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        add(tfUser, gridBagConstraints);

        luser.setText("User:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        add(luser, gridBagConstraints);

        pfPassword.setPreferredSize(new java.awt.Dimension(10, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        add(pfPassword, gridBagConstraints);

        lpass.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 0, 0);
        add(lpass, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        bCancel.setText("Cancel");
        bCancel.setPreferredSize(new java.awt.Dimension(120, 35));
        jPanel1.add(bCancel);

        bEnter.setText("Sign in");
        bEnter.setPreferredSize(new java.awt.Dimension(120, 35));
        jPanel1.add(bEnter);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(jPanel1, gridBagConstraints);

        lLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lLogo.setText("<html>\nWelcome to\n<br>\n<font size = '5'> SwingPlayground</font>\n</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(lLogo, gridBagConstraints);

        lFooter.setFont(new java.awt.Font("Lucida Sans", 0, 10));
        lFooter.setText("Made by https://github.com/d0tplist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(lFooter, gridBagConstraints);
    }


    private javax.swing.JButton bCancel;
    private javax.swing.JButton bEnter;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lFooter;
    private javax.swing.JLabel lLogo;
    private javax.swing.JLabel lpass;
    private javax.swing.JLabel luser;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfUser;
}
