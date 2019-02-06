package com.d0tplist;

import com.d0tplist.cases.Complex;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private final Stroke in;
    private boolean drawGrid = true;

    @SuppressWarnings("all")
    public GridPanel() {
        super.setOpaque(false);

        super.setForeground(Color.LIGHT_GRAY);

        in = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f,
                new float[]{1, 0, 1}, 2.0f);

        JPopupMenu jPopupMenu = new JPopupMenu();

        JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("Show Grid");
        checkBoxMenuItem.setSelected(true);

        JMenuItem menuItemRemove = new JMenuItem("Remove content");
        menuItemRemove.addActionListener(event -> PlaygroundWindow.instance.load(null));

        checkBoxMenuItem.addActionListener(event -> {
            drawGrid = checkBoxMenuItem.isSelected();
            repaint();
        });

        JMenuItem menuBackground = new JMenuItem("Change background");
        JMenuItem menuForeground = new JMenuItem("Change grid color");
        JMenuItem menuComplex = new JMenuItem("Add complex example");
        JMenuItem menuExample = new JMenuItem("Example");

        menuBackground.addActionListener(event -> {
            Color select_background = JColorChooser.showDialog(this, "Select background", Color.WHITE);

            if (select_background != null) {
                setBackground(select_background);
            }
        });

        menuForeground.addActionListener(event -> {
            Color select_background = JColorChooser.showDialog(this, "Select grid color", Color.LIGHT_GRAY);

            if (select_background != null) {
                setForeground(select_background);
            }
        });

        menuExample.addActionListener(event -> {
            JLabel label = new JLabel("This an example");
            label.setIcon(new ImageIcon(GridPanel.class.getResource("/icons/doge.jpg")));
            PlaygroundWindow.instance.load(label);
        });


        menuComplex.addActionListener(event -> {
            PlaygroundWindow.instance.load(new Complex());
        });

        jPopupMenu.add(checkBoxMenuItem);
        jPopupMenu.add(menuItemRemove);
        jPopupMenu.add(menuBackground);
        jPopupMenu.add(menuForeground);
        jPopupMenu.add(menuComplex);
        jPopupMenu.add(menuExample);

        setComponentPopupMenu(jPopupMenu);

    }

    @Override
    public void setOpaque(boolean isOpaque) {

    }

    @Override
    public void paint(Graphics gr) {

        Graphics2D g = (Graphics2D) gr;

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        if (!drawGrid) {
            super.paintChildren(g);
            return;
        }

        g.setColor(getForeground());

        g.setStroke(in);

        for (int i = 0; i < getHeight(); i += 20) {
            g.drawLine(0, i, getWidth(), i);
        }

        g.setStroke(in);

        for (int i = 0; i < getWidth(); i += 20) {
            g.drawLine(i, 0, i, getHeight());
        }

        super.paintChildren(g);
    }
}
