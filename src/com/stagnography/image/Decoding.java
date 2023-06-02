package com.stagnography.image;

import java.util.ArrayList;

public class Decoding {
    String path;

    public Decoding(String path) {
        this.path = path;
    }

    public String decode() {
        final var data = Image.readImage(path);
        StringBuilder message = new StringBuilder();
        for (ArrayList<String> ith : data) {
            for (String jth : ith) {
                message.append(jth.charAt(7));
            }
        }
        return Text.binaryToStr(message.toString());
    }
}
