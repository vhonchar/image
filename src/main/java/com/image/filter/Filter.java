package com.image.filter;

import java.awt.image.BufferedImage;
import java.util.Observer;

public interface Filter {

    void setNext(Filter filter);

    void process(BufferedImage image);

    void addObserver(Observer observer);
}
