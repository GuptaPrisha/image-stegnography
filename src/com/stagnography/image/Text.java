package com.stagnography.image;

public class Text {
    static String strToBinary(String s) {
        StringBuilder bin = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i);
            StringBuilder letterBin = new StringBuilder();
            while (val > 0) {
                if (val % 2 == 1) {
                    letterBin.append('1');
                } else
                    letterBin.append('0');
                val /= 2;
            }
            while (letterBin.length() < 8) {
                letterBin.append("0");
            }
            letterBin = new StringBuilder(reverse(letterBin.toString()));
            bin.append(letterBin);
        }
        return bin.toString();
    }

    static String intToBinary(int bin) {
        StringBuilder num = new StringBuilder(Integer.toBinaryString(bin));
        while (num.length() < 8) {
            num.insert(0, "0");
        }
        return num.toString();
    }

    static int binaryStringToInt(String bin) {
        int val = 0;
        for (int j = 7; j >= 0; j--) {
            if (bin.charAt(j) == '1') {
                val += Math.pow(2, 7 - j);
            }
        }
        return val;
    }

    static String reverse(String input) {
        char[] a = input.toCharArray();
        int l, r = 0;
        r = a.length - 1;

        for (l = 0; l < r; l++, r--) {
            // Swap values of l and r
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }

    static String binaryToStr(String bin) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < bin.length(); i = i + 8) {
            int val = 0;
            for (int j = 7; j >= 0; j--) {
                if (bin.charAt(i + j) == '1') {
                    val += Math.pow(2, 7 - j);
                }
            }
            var uni = (char) val;
            if (uni == Constants.delimiter) {
                return message.toString();
            }
            message.append(uni);
        }
        return message.toString();
    }

}
