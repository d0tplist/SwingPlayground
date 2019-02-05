package com.d0tplist;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private final Stroke in;

    public GridPanel() {
        super.setOpaque(false);

        super.setPreferredSize(new Dimension(200, 200));

        in = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f,
                new float[]{1, 0, 1}, 2.0f);

    }

    @Override
    public void setOpaque(boolean isOpaque) {

    }

    @SuppressWarnings("all")
    @Override
    public void paint(Graphics gr) {

        Graphics2D g = (Graphics2D) gr;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());


        g.setColor(Color.LIGHT_GRAY);

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
