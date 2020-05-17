package com.company;

import netscape.javascript.JSObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JComponent component;
    JLabel label;
    JLabel label2;
    JComboBox<String> componentsField;
    JTextField name;
    JButton generateButton;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Select a Swing component:");
        label2 = new JLabel("Name:");
        componentsField = new JComboBox<String>();
        componentsField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        generateButton = new JButton("Generate");

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = getObjectName();
                Method[] methods = component.getClass().getMethods();
                for (Method method : methods) {
                    if (method.toString().contains(".setText")) {
                        try {
                            method.invoke(component,name);
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if (component instanceof JButton) {
                    System.out.println("It is a button");
                }
                Random rand = new Random();
                int x = rand.nextInt(500);
                int y = rand.nextInt(400);
                Dimension sizeObject = component.getPreferredSize();
                component.setBounds(x, y, sizeObject.width, sizeObject.height);
                frame.designPanel.add(component);
                revalidate();
            }
        });

        componentsField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (!componentsField.getSelectedItem().toString().equals("Choose")) {
                        String object = "javax.swing.J" + componentsField.getSelectedItem().toString();
                        try {
                            Class classObject = Class.forName(object);
                            JComponent objectGenerate = (JComponent) classObject.getConstructor().newInstance();
                            component = objectGenerate;
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException /*| IOException*/ ex) {
                            System.out.println(ex.toString());
                        }
                    }
                }
            }
        });

        componentsField.addItem("Choose");
        componentsField.addItem("Button");
        componentsField.addItem("CheckBox");
        componentsField.addItem("ComboBox");
        componentsField.addItem("List");
        componentsField.addItem("Menu");
        componentsField.addItem("RadioButton");
        componentsField.addItem("Slider");
        componentsField.addItem("Spinner");
        componentsField.addItem("TextField");
        componentsField.addItem("PasswordField");
        componentsField.addItem("ColorChooser");
        componentsField.addItem("EditorPane");
        componentsField.addItem("TextPane");
        componentsField.addItem("FileChooser");
        componentsField.addItem("Table");
        componentsField.addItem("TextArea");
        componentsField.addItem("Tree");

        name = new JTextField("Insert a name");

        add(label);
        add(componentsField);
        add(label2);
        add(name);
        add(generateButton);
    }

    private String getObjectName() {
        String objectName = name.getText();
        return objectName;
    }

}
