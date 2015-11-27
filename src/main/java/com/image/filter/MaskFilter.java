package com.image.filter;

import com.image.model.BorderSegment;
import com.image.model.Pixel;
import com.image.model.Segment;

import java.awt.image.BufferedImage;

public class MaskFilter extends AbstractFilter {

    private final int[][] GX;
    private final int[][] GY;

    public MaskFilter(int[][] GX, int[][] GY) {
        this.GX = GX;
        this.GY = GY;
    }

    @Override
    protected void doFilter(BufferedImage image) {
        BufferedImage resultImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 1; x < image.getWidth() - 1; x++) {
            for (int y = 1; y < image.getHeight() - 1; y++) {
                Segment segment = getSegment(image, x, y);

                Pixel horizontalFilteredValue = segment.applyFilterMatrix(GX) ;
                Pixel verticalFilteredValue = segment.applyFilterMatrix(GY);

                Pixel result = new Pixel(
                        (int) Math.sqrt(Math.pow(horizontalFilteredValue.getR(), 2) + Math.pow(verticalFilteredValue.getR(), 2)),
                        (int) Math.sqrt(Math.pow(horizontalFilteredValue.getG(), 2) + Math.pow(verticalFilteredValue.getG(), 2)),
                        (int) Math.sqrt(Math.pow(horizontalFilteredValue.getB(), 2) + Math.pow(verticalFilteredValue.getB(), 2))
                );

//                int resultFilteredValue = (int) Math.sqrt(Math.pow(horizontalFilteredValue, 2) + Math.pow(verticalFilteredValue, 2));

                resultImage.setRGB(x, y, result.getRGB());
            }
        }

        image.setData(resultImage.getData());
    }

    private Segment getSegment(BufferedImage image, int x, int y) {
        return isPositionNearBorder(image, x, y) ? new BorderSegment(image, x - 1, y - 1, GX.length) : new Segment(image, x - 1, y - 1, GX.length);
    }

    private boolean isPositionNearBorder(BufferedImage image, int x, int y) {
        return x ==0 || y == 0 || x == (image.getWidth() - 1) || (y == image.getHeight() - 1);
    }
}
