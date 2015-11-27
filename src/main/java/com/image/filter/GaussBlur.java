package com.image.filter;

import com.image.model.Pixel;

import java.awt.image.BufferedImage;

/**
 * Created by Volodymyr_Honchar on 05.11.2015.
 */
public class GaussBlur extends AbstractFilter {

    public static final int RADIUS_OF_DEGRADATION = 3;
    private static final int GAUSS_DEFLECTION_OF_DEGRADATION = 1;

    private int radius;

    private double[] segmentCoefficients;

    public GaussBlur(int radiusOfDegradation) {
        this.radius = radiusOfDegradation;
        this.segmentCoefficients = initSegmentCoefficients(radiusOfDegradation);
    }

    private double[] initSegmentCoefficients(int radius) {
        double[] coef = new double[6 * radius + 1]; // 2*(3*r) + 1(central pixel)
        for (int i = 0; i < coef.length / 2 + 1; i++) {
            coef[coef.length / 2 + i] = Math.exp(-(i * i) / (2 * radius * radius));
            coef[coef.length / 2 - i] = coef[coef.length / 2 + i];
        }
        return coef;
    }

    @Override
    protected void doFilter(BufferedImage image) {
        BufferedImage resultImage = horizontalBlur(image);
        resultImage = verticalBlur(resultImage);
        image.setData(resultImage.getData());
    }

    private BufferedImage horizontalBlur(BufferedImage image) {
        BufferedImage resultImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                double sum = 0;
                Pixel rgbSum = new Pixel(0, 0, 0);
                for (int i = 0; i < segmentCoefficients.length; i++) {
                    int pixelShift = i - radius;
                    if ((x + pixelShift) >= 0 && (x + pixelShift) < image.getWidth()) {
                        int rgb = image.getRGB(x + pixelShift, y);
                        Pixel pixel = Pixel.fromRGB(rgb);

                        rgbSum.setR((int) (rgbSum.getR() + pixel.getR() * segmentCoefficients[i]));
                        rgbSum.setG((int) (rgbSum.getG() + pixel.getG() * segmentCoefficients[i]));
                        rgbSum.setB((int) (rgbSum.getB() + pixel.getB() * segmentCoefficients[i]));
                        sum += segmentCoefficients[i];
                    }

                }
                rgbSum.setR((int) (rgbSum.getR() / sum));
                rgbSum.setG((int) (rgbSum.getG() / sum));
                rgbSum.setB((int) (rgbSum.getB() / sum));

                resultImage.setRGB(x, y, rgbSum.getRGB());
            }
        }
        return resultImage;
    }

    private BufferedImage verticalBlur(BufferedImage image) {
        BufferedImage resultImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                double sum = 0;
                Pixel rgbSum = new Pixel(0, 0, 0);
                for (int i = 0; i < segmentCoefficients.length; i++) {
                    int pixelShift = i - radius;
                    if ((y + pixelShift) >= 0 && (y + pixelShift) < image.getHeight()) {
                        int rgb = image.getRGB(x, y + pixelShift);
                        Pixel pixel = new Pixel(
                                (rgb >> 16) & 0xFF,
                                (rgb >> 8) & 0xFF,
                                rgb & 0xFF
                        );
                        rgbSum.setR((int) (rgbSum.getR() + pixel.getR() * segmentCoefficients[i]));
                        rgbSum.setG((int) (rgbSum.getG() + pixel.getG() * segmentCoefficients[i]));
                        rgbSum.setB((int) (rgbSum.getB() + pixel.getB() * segmentCoefficients[i]));
                        sum += segmentCoefficients[i];
                    }

                }
                rgbSum.setR((int) (rgbSum.getR() / sum));
                rgbSum.setG((int) (rgbSum.getG() / sum));
                rgbSum.setB((int) (rgbSum.getB() / sum));

                resultImage.setRGB(x, y, rgbSum.getRGB());
            }
        }
        return resultImage;
    }
}
