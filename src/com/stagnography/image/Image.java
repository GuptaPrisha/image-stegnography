package com.stagnography.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Image {
    static ArrayList<Integer> getImageDimentions(String path) {
        File file = new File(path);
        try {

            BufferedImage img = ImageIO.read(file);
            ArrayList<Integer> imgDimentions = new ArrayList<>();
            imgDimentions.add(img.getWidth());
            imgDimentions.add(img.getHeight());
            return imgDimentions;
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
        return new ArrayList<>();
    }

    // reads the image as 2d array (pixel color values in binary string)
    static ArrayList<ArrayList<String>> readImage(String path) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedImage img = ImageIO.read(file);
            /*
             * Looping over the pixel grid to get rgb values of each pixel,
             * and then collect it in the "data" array.
             */
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    int pixel = img.getRGB(x, y);

                    Color color = new Color(pixel, true);
                    ArrayList<String> rgbArray = new ArrayList<>();
                    rgbArray.add(Text.intToBinary(color.getRed()));
                    rgbArray.add(Text.intToBinary(color.getGreen()));
                    rgbArray.add(Text.intToBinary(color.getBlue()));

                    data.add(rgbArray);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    static void writeImage(ArrayList<ArrayList<Integer>> data, String oldPath, String newPath) {
        var imgDimentions = getImageDimentions(oldPath);
        BufferedImage image = new BufferedImage(imgDimentions.get(0), imgDimentions.get(1), BufferedImage.TYPE_INT_RGB);

        for (int x = 0, k = 0; x < imgDimentions.get(0); x++) {
            for (int y = 0; y < imgDimentions.get(1); y++, k++) {
                final var rgbArray = data.get(k); /// [255,255,255]
                Color color = new Color(rgbArray.get(0), rgbArray.get(1), rgbArray.get(2));
                image.setRGB(x, y, color.getRGB());
            }
        }

        File ImageFile = new File(newPath);
        try {
            ImageIO.write(image, "png", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
