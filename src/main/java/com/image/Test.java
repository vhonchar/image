package com.image;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Volodymyr_Honchar on 02.11.2015.
 */
public class Test {

    private static ComputerVisionFacade facade = ComputerVisionFacade.getInstance();

    public static void main(String[] args) throws IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

//        facade.detectEdge(new URL("http://topgir.com.ua/wp-content/uploads/2013/05/nomer_0000.jpg"));
        facade.detectEdge(new URL("http://dumskaya.net/pics/b2/picturepicture_5166951939867_17142.JPG"));

//        facade.compareImages(cl.getResource("image.jpg"), cl.getResource("image2.jpg"));
    }
}
