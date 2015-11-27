package com.image.ui;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Volodymyr_Honchar on 02.11.2015.
 */
public class FrameWithImage implements Observer {

    private final ImageIcon imageIcon;
    private JLabel label;
    private JFrame frame;


    public FrameWithImage(BufferedImage image){
        this.imageIcon = initComponents(image);
    }

    private ImageIcon initComponents(final BufferedImage image){
        this.frame = new JFrame("Difference");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon(image);

        label = new JLabel(imageIcon);

        frame.add(label);
        frame.pack();
        frame.setVisible(true);

        return imageIcon;
    }

    public void update(Observable o, Object arg) {
        BufferedImage image = (BufferedImage) arg;
        label.setIcon(new ImageIcon(image));
    }
}

