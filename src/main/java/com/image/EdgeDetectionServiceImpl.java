package com.image;

import com.image.filter.*;

import java.awt.image.BufferedImage;
import java.util.Observer;

/**
 * Created by Volodymyr_Honchar on 02.11.2015.
 */
public class EdgeDetectionServiceImpl implements EdgeDetectionService {

    private static EdgeDetectionService instance = new EdgeDetectionServiceImpl();

    public static EdgeDetectionService getInstance() {
        return instance;
    }

    // Horizontal mask Of Sobel's operator
    private final int[][] GX = new int[][]{
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
    };

    // Vertical mask Of Sobel's operator
    private final int[][] GY = new int[][]{
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
    };

    private EdgeDetectionServiceImpl() {
    }

    public BufferedImage detectEdge(BufferedImage image, Observer observer) {
        return detectEdge(image, observer, 2);
    }

    public BufferedImage detectEdge(BufferedImage image, Observer observer, int radiusOfDegradation){
        FilterChain.createChain(observer)
                .add(new GrayscaleFilter())
                .add(new GaussBlur(radiusOfDegradation))
                .applyMask(GX, GY)
                .execute(image);

        return image;
    }
}
