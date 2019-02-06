package com.d0tplist.cases;

import javax.swing.*;
import java.awt.*;

public class ButtonTest extends JButton {

    private ButtonTest() {

        setText("Click me");

        super.setPreferredSize(new Dimension(100, 100));

        addActionListener(event -> JOptionPane.showMessageDialog(this, "its like magic!"));

    }
}
