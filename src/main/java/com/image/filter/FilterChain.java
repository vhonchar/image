package com.image.filter;

import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Observer;

/**
 * Created by Volodymyr_Honchar on 05.11.2015.
 */
public class FilterChain {

    public static FilterChain createChain(Observer observer){
        return new FilterChain(observer);
    }

    private Observer observer;
    private Filter firstOne;
    private Filter lastOne;

    public FilterChain(Observer observer) {
        this.observer = observer;
    }

    public FilterChain add(Filter filter){
        filter.addObserver(this.observer);
        if(firstOne == null){
            this.firstOne = filter;
            this.lastOne = filter;
        } else {
            this.lastOne.setNext(filter);
            this.lastOne = filter;
        }
        return this;
    }

    public void execute(BufferedImage image){
        firstOne.process(image);
    }

    public FilterChain applyMask(int[][] gx, int[][] gy) {
        return this.add(new MaskFilter(gx, gy));
    }
}
