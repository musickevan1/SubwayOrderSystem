/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subwayordersystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class SubwayOrderSystem {
    
    private List<JComboBox> comboBoxes = new ArrayList<>();

    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new SubwayOrderSystem().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Subway Order System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Set the frame icon
        try {
            File iconFile = new File("C:\\Users\\diane\\OneDrive\\Documents\\NetBeansProjects\\SubwayOrderSystem\\resources\\icon.png");
            if (iconFile.exists()) {
                ImageIcon icon = new ImageIcon(iconFile.getAbsolutePath());
                frame.setIconImage(icon.getImage());
            } else {
                System.out.println("Icon not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add the Subway logo
        try {
            BufferedImage logoImage = ImageIO.read(new File("resources/subway_logo.png"));
            int originalWidth = logoImage.getWidth();
            int originalHeight = logoImage.getHeight();
            int newWidth = 200;
            int newHeight = (int) (((double) newWidth / originalWidth) * originalHeight);
            Image scaledLogoImage = logoImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogoImage));
            frame.add(logoLabel, BorderLayout.NORTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Apply Subway branding colors
        Color vividGreen = new Color(0, 140, 21);
        Color marigold = new Color(255, 198, 0);
        frame.getContentPane().setBackground(vividGreen);

        // Create the order panel
        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(vividGreen);
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        // Font for labels
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Add labeled panels for each JComboBox
        String[] sandwichSizes = {"6 inch", "12 inch"};
        String[] breadTypes = {"White", "Wheat", "Italian", "Honey Oat"};
        String[] meatTypes = {"Turkey", "Ham", "Roast Beef", "Tuna"};
        String[] cheeseTypes = {"American", "Swiss", "Provolone", "Cheddar"};
        String[] veggies1 = {"Lettuce", "Tomatoes", "Cucumbers", "Green Peppers"};
        String[] veggies2 = {"Spinach", "Olives", "Pickles", "Onions"};
        String[] veggies3 = {"Jalapenos", "Banana Peppers", "Avocado", "None"};
        String[] sauceTypes = {"Mayo", "Mustard", "Honey Mustard", "Ranch"};

        orderPanel.add(createLabeledPanel("Sandwich Size:", new JComboBox<>(sandwichSizes), vividGreen, labelFont, marigold));
        orderPanel.add(createLabeledPanel("Bread Type:", new JComboBox<>(breadTypes), vividGreen, labelFont, marigold));
        orderPanel.add(createLabeledPanel("Meat Type:", new JComboBox<>(meatTypes), vividGreen, labelFont, marigold));
        orderPanel.add(createLabeledPanel("Cheese Type:", new JComboBox<>(cheeseTypes), vividGreen, labelFont, marigold));
        orderPanel.add(createLabeledPanel("Veggies 1:", new JComboBox<>(veggies1), vividGreen, labelFont, marigold));
        orderPanel.add(createLabeledPanel("Veggies 2:", new JComboBox<>(veggies2), vividGreen, labelFont, marigold));
        orderPanel.add(createLabeledPanel("Veggies 3:", new JComboBox<>(veggies3), vividGreen, labelFont, marigold));
        orderPanel.add(createLabeledPanel("Sauce Type:", new JComboBox<>(sauceTypes), vividGreen, labelFont, marigold));

        JCheckBox saltAndPepper = new JCheckBox("Salt & Pepper");
        saltAndPepper.setBackground(vividGreen);
        saltAndPepper.setForeground(marigold);
        orderPanel.add(createLabeledPanel("Extras:", saltAndPepper, vividGreen, labelFont, marigold));

        frame.add(orderPanel, BorderLayout.CENTER);

        // Create submit button
        JButton submitButton = new JButton("Submit Order");
        submitButton.setBackground(marigold);
        submitButton.setForeground(Color.BLACK);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder summary = new StringBuilder();
                summary.append("Sandwich Size: ").append(comboBoxes.get(0).getSelectedItem()).append("\n");
                summary.append("Bread Type: ").append(comboBoxes.get(1).getSelectedItem()).append("\n");
                summary.append("Meat Type: ").append(comboBoxes.get(2).getSelectedItem()).append("\n");
                summary.append("Cheese Type: ").append(comboBoxes.get(3).getSelectedItem()).append("\n");
                summary.append("Veggies: ").append(comboBoxes.get(4).getSelectedItem()).append(", ");
                summary.append(comboBoxes.get(5).getSelectedItem()).append(", ");
                summary.append(comboBoxes.get(6).getSelectedItem()).append("\n");
                summary.append("Sauce: ").append(comboBoxes.get(7).getSelectedItem()).append("\n");
                summary.append("Salt & Pepper: ").append(saltAndPepper.isSelected() ? "Yes" : "No");

                JOptionPane.showMessageDialog(frame, summary.toString(), "Order Summary", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(submitButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createLabeledPanel(String labelText, Component component, Color bgColor, Font font, Color fgColor) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(bgColor);

        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setForeground(fgColor);
        panel.add(label);
        panel.add(component);

        if (component instanceof JComboBox) {
            comboBoxes.add((JComboBox) component);
        }

        return panel;
    }

}
