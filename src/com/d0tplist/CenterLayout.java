package com.d0tplist;

import java.awt.*;

public class CenterLayout implements LayoutManager {
    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(100, 100);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(0, 0);
    }

    @Override
    public void layoutContainer(Container parent) {

        for (Component component : parent.getComponents()) {
            component.setBounds(parent.getWidth() / 2 - (component.getPreferredSize().width / 2),
                    parent.getHeight() / 2 - (component.getPreferredSize().height / 2),
                    component.getPreferredSize().width, component.getPreferredSize().height);
        }

    }
}
