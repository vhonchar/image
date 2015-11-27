package com.image.model;

/**
 * Created by Volodymyr_Honchar on 02.11.2015.
 */
public class Coordinates {

    public static final int MIN_DISTANCE = 5;
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isNear(Coordinates coord) {
        return (Math.abs(x - coord.getX()) <= MIN_DISTANCE)
                && (Math.abs(y - coord.getY()) <= MIN_DISTANCE);
    }
}
