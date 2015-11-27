package com.image.filter;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Volodymyr_Honchar on 04.11.2015.
 */
public abstract class AbstractFilter extends Observable implements Filter {

    private Filter nextFilter;

    public void setNext(Filter filter) {
        this.nextFilter = filter;
    }

    public void process(BufferedImage image) {
        doFilter(image);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        setChanged();
        notifyObservers(image);

        if (nextFilter != null) {
            nextFilter.process(image);
        }
    }

    protected abstract void doFilter(BufferedImage image);
}
