package com.image.model;

import java.awt.image.BufferedImage;

/**
 * Created by Volodymyr_Honchar on 04.11.2015.
 */
public class Segment {

    protected Pixel[][] segment;

    public Segment(BufferedImage image, int xStart, int yStart, int size) {

        this.segment = new Pixel[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int rgb = image.getRGB(xStart + i, yStart + j);
                segment[i][j] = Pixel.fromRGB(rgb);
            }
        }
    }

    protected Segment(){
    }

    public Pixel applyFilterMatrix(int[][] filter) {
        if(filter.length != segment.length || segment[0].length != filter[0].length){
            throw new IllegalArgumentException("Wrong filter dimension");
        }

        Pixel result = new Pixel(0, 0, 0);
        for(int i = 0; i < segment.length; i++){
            for(int j = 0; j < segment[i].length; j++){
                result.setR(result.getR() + segment[i][j].getR() * filter[i][j]);
                result.setG(result.getG() + segment[i][j].getG() * filter[i][j]);
                result.setB(result.getB() + segment[i][j].getB() * filter[i][j]);
            }
        }

        return result;
    }
}
