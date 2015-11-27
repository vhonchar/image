package com.image.ui;

import java.awt.image.BufferedImage;

/**
 * Created by Volodymyr_Honchar on 02.11.2015.
 */
public class SwingUI implements UI {

    private static UI instance = new SwingUI();

    public static UI getInstance() {
        return instance;
    }

    private SwingUI(){}

    public FrameWithImage openInNewWindow(final BufferedImage image){

        return new FrameWithImage(image);
    }

}
