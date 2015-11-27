package com.image.filter;

import com.image.model.Pixel;

import java.awt.image.BufferedImage;

/**
 * Created by Volodymyr_Honchar on 05.11.2015.
 */
public class GrayscaleFilter extends AbstractFilter{


    @Override
    protected void doFilter(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                Pixel pixel = Pixel.fromRGB(rgb);

                int grayscaledColor = (int) (0.2 * pixel.getR() + 0.7 * pixel.getG() + 0.1 * pixel.getB());
//                int grayscaledColor = (int) (0.3 * (pixel.getR() + pixel.getG() + pixel.getB()));
                image.setRGB(x, y, new Pixel(grayscaledColor, grayscaledColor, grayscaledColor).getRGB());
//                image.setRGB(x, y, grayscaledColor);
            }
        }
    }
}
