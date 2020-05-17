package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;

    public MainFrame() {
        super("Objects");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        designPanel = new DesignPanel(this);
        controlPanel = new ControlPanel(this);

        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);

        pack();
    }
}
