package com.image;

import com.image.model.Coordinates;
import com.image.model.Pixel;
import com.image.model.CoordinatesGroup;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volodymyr_Honchar on 02.11.2015.
 */
public class CompareImageService {

    public static final int RED = 0xFD0000;
    private static CompareImageService instance = new CompareImageService();

    public static CompareImageService getInstance() {
        return instance;
    }

    private CompareImageService() {
    }

    public BufferedImage drawDifference(BufferedImage image, BufferedImage anotherImage) {

        List<Coordinates> differentCoordinates = getCoordinatesWithDifferentRGB(image, anotherImage);
        List<CoordinatesGroup> groups = groupCoordinates(differentCoordinates);
        drawGroupsAsRects(image, groups);

        return image;
    }

    private List<Coordinates> getCoordinatesWithDifferentRGB(BufferedImage image, BufferedImage anotherImage) {
        List<Coordinates> differentCoordinates = new ArrayList<Coordinates>();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int anotherRgb = anotherImage.getRGB(x, y);

                Pixel pixel1 = new Pixel(
                        (rgb >> 16) & 0xFF,
                        (rgb >> 8) & 0xFF,
                        rgb & 0xFF
                );
                Pixel pixel2 = new Pixel(
                        (anotherRgb >> 16) & 0xFF,
                        (anotherRgb >> 8) & 0xFF,
                        anotherRgb & 0xFF
                );

                if (!pixel1.equals(pixel2)) {
                    differentCoordinates.add(new Coordinates(x, y));
//                    image.setRGB(x,y, RED);
//                    System.out.println(String.format("Different pixels at (%s,%s): %s, %s", x, y, pixel1.toString(), pixel2.toString()));
                }
            }
        }
        return differentCoordinates;
    }

    private List<CoordinatesGroup> groupCoordinates(List<Coordinates> coordinatesList) {
        List<CoordinatesGroup> groups = new ArrayList<CoordinatesGroup>();

        while (!coordinatesList.isEmpty()) {
            groups.add(createGroup(coordinatesList));
        }

        return groups;
    }

    private CoordinatesGroup createGroup(List<Coordinates> coordinatesList) {
        CoordinatesGroup coordinatesGroup = new CoordinatesGroup(coordinatesList.remove(0));
        boolean haveAdded;
        do {
            haveAdded = false;
            for (int i = 0; i < coordinatesList.size(); i++) {
                if (coordinatesGroup.canAdd(coordinatesList.get(i))) {
                    coordinatesGroup.add(coordinatesList.remove(i));
                    haveAdded = true;
                }
            }
        } while (haveAdded);
        return coordinatesGroup;
    }

    private void drawGroupsAsRects(BufferedImage image, List<CoordinatesGroup> pixelsGroups) {
        for (CoordinatesGroup pixelsGroup : pixelsGroups) {
            Graphics graphics = image.getGraphics();
            graphics.setColor(new Color(RED));
            graphics.drawRect(pixelsGroup.getX(), pixelsGroup.getY(), pixelsGroup.getWidth(), pixelsGroup.getHeight());
        }
    }

}
