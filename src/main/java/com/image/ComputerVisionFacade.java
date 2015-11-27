package com.image;

import com.image.ui.FrameWithImage;
import com.image.ui.SwingUI;
import com.image.ui.UI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Volodymyr_Honchar on 09.11.2015.
 */
public class ComputerVisionFacade {

    private static final ComputerVisionFacade instance = new ComputerVisionFacade();

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    private EdgeDetectionService edgeDetectionService = EdgeDetectionServiceImpl.getInstance();

    private UI ui = SwingUI.getInstance();
    private CompareImageService compareImageService = CompareImageService.getInstance();

    public static ComputerVisionFacade getInstance(){
        return instance;
    }

    private ComputerVisionFacade(){}

    public void detectEdge(URL url) throws IOException {
        final BufferedImage image = ImageIO.read(url);
        final FrameWithImage frameWithImage = ui.openInNewWindow(image);

        threadPool.execute(new Runnable() {
            public void run() {
                edgeDetectionService.detectEdge(image, frameWithImage);
            }
        });
    }

    public void compareImages(URL url, URL url2) throws IOException {
        final BufferedImage image = ImageIO.read(url);
        final BufferedImage image2 = ImageIO.read(url2);

        BufferedImage resultImage = compareImageService.drawDifference(image, image2);
        ui.openInNewWindow(resultImage);
    }

    public void detectEdge(URL url, final Observer observer) throws IOException {
        final BufferedImage image = ImageIO.read(url);

        threadPool.execute(new Runnable() {
            public void run() {
                edgeDetectionService.detectEdge(image, observer);
            }
        });
    }

}
