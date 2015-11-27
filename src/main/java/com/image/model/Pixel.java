package com.image.model;

import java.awt.*;

/**
 * Created by Volodymyr_Honchar on 02.11.2015.
 */
public class Pixel {

    public static Pixel fromRGB(int rgb){
        return new Pixel(
                (rgb >> 16) & 0xFF,
                (rgb >> 8) & 0xFF,
                rgb & 0xFF
        );
    }

    public static final int DIFFERENCE_MAXIMUM = 10;
    public static final int HUE = 0;

    private float brightness;
    private float s;
    private float h;
    private int r;
    private int g;
    private int b;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        float[] hsb = Color.RGBtoHSB(r, g, b, new float[3]);

        this.h = hsb[0];
        this.s = hsb[1];
        this.brightness = hsb[2];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel anotherPixel = (Pixel) o;

//        float[] hsb = Color.RGBtoHSB(r, g, b, new float[3]);
//        float[] hsb1 = Color.RGBtoHSB(anotherPixel.r, anotherPixel.g, anotherPixel.b, new float[3]);
        double rDiff = getDiffInPercents(r, anotherPixel.r);
        double gDiff = getDiffInPercents(g, anotherPixel.g);
        double bDiff = getDiffInPercents(b, anotherPixel.b);

//        return Math.abs(hsb[HUE] - hsb1[HUE]) * 100 < DIFFERENCE_MAXIMUM;
        return (rDiff + gDiff + bDiff) < DIFFERENCE_MAXIMUM;
    }

    private double getDiffInPercents(int number, int anotherNumber) {

        int min = Math.min(number, anotherNumber);
        int dif = Math.abs(number - anotherNumber);

        return dif == 0 ? 0 : ((double)min / dif) * 100;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s,%s)", r, g, b);
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getRGB() {
        return (r << 16) | (g << 8) | b;
    }

    public float getBrightness() {
        return brightness * 100;
    }

    public float getS() {
        return s;
    }

    public float getH() {
        return h;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }
}
