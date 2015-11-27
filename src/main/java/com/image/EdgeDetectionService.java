package com.image;

import java.awt.image.BufferedImage;
import java.util.Observer;

public interface EdgeDetectionService {

    BufferedImage detectEdge(BufferedImage image, Observer observer);
    BufferedImage detectEdge(BufferedImage image, Observer observer, int radiusOfDegradation);

}
