package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DesignPanel extends JPanel {
    public JComponent component;
    final static int W = 800, H = 600;
    final MainFrame frame;
    BufferedImage image;
    Graphics2D graphics;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        Random x = new Random(700);
        Random y = new Random(500);

        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }

    public void generateObject(JComponent component, int x, int y) {
        Dimension sizeObject = component.getPreferredSize();
        component.setBounds(x, y, sizeObject.width, sizeObject.height);
        add(component);
    }
}
