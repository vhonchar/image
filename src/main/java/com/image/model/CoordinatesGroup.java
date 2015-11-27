package com.image.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Volodymyr_Honchar on 02.11.2015.
 */
public class CoordinatesGroup {

    private List<Coordinates> coordinatseList = new ArrayList<Coordinates>();

    public CoordinatesGroup(Coordinates coordinats) {
        coordinatseList.add(coordinats);
    }

    public boolean canAdd(Coordinates newCoord) {
        for(Coordinates coordinats: coordinatseList){
            if(coordinats.isNear(newCoord)){
                return true;
            }
        }
        return false;
    }

    public void add(Coordinates coordinats) {
        coordinatseList.add(coordinats);
    }

    public int getX() {
        return Collections.min(coordinatseList, new CompareByXCoord()).getX();
    }

    public int getY() {
        return Collections.min(coordinatseList, new CompareByYCoord()).getY();
    }

    public int getWidth() {
        return Math.abs(Collections.min(coordinatseList, new CompareByXCoord()).getX() - Collections.max(coordinatseList, new CompareByXCoord()).getX());
    }

    public int getHeight() {
        return Math.abs(Collections.min(coordinatseList, new CompareByYCoord()).getY() - Collections.max(coordinatseList, new CompareByYCoord()).getY());
    }

    private static class CompareByXCoord implements Comparator<Coordinates>{

        public int compare(Coordinates o1, Coordinates o2) {
            return o1.getX() - o2.getX();
        }
    }

    private static class CompareByYCoord implements Comparator<Coordinates>{

        public int compare(Coordinates o1, Coordinates o2) {
            return o1.getY() - o2.getY();
        }
    }
}
