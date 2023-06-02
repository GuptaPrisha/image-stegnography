package com.stagnography.image;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Encoding {
    String path;

    public Encoding(String path) {
        this.path = path;
    }

    static ArrayList<ArrayList<Integer>> convertBinaryStringArrayToIntArray(ArrayList<ArrayList<String>> data) {
        var finalData = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<String> ith : data) {
            var newIth = new ArrayList<Integer>();
            for (String jth : ith) {
                var newJth = Text.binaryStringToInt(jth);
                newIth.add(newJth);
            }
            finalData.add(newIth);
        }
        return finalData;
    }

    public void encode(String messageStr,String newPath) {
        final var data = Image.readImage(path);
        messageStr += Constants.delimiter;
        var message = Text.strToBinary(messageStr).toCharArray();
        var binaryMessageList = new ArrayList<Character>();
        for (char c : message)
            binaryMessageList.add(c);

        for (int i = 0, k = 0; i < data.size() && k < binaryMessageList.size(); i++) {
            var ith = data.get(i);
            for (int j = 0; j < data.get(i).size() && k < binaryMessageList.size(); j++, k++) {
                String jth = ith.get(j);
                StringBuilder builder = new StringBuilder(jth);
                builder.setCharAt(7, binaryMessageList.get(k)); //here:
                var encoded = builder.toString();
                ith.set(j, encoded);
            }
            data.set(i, ith);
        }

        Image.writeImage(convertBinaryStringArrayToIntArray(data), path, newPath);
    }
}
