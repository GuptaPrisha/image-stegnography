package com.stagnography.image;

import java.util.Scanner;

public class Main {
    static void menu(int a) {
        switch (a) {
            case 1 -> {
                System.out.println("please specify path for the image u need to add text to");
                Scanner sc = new Scanner(System.in);
                String ePath = sc.nextLine();
                System.out.println("specify path for the output image");
                Scanner sc1 = new Scanner(System.in);
                String newPath = sc1.nextLine();
                System.out.println("please enter the message u need to encode");
                Scanner sc2 = new Scanner(System.in);
                String message = sc2.nextLine();
                Encoding encoding = new Encoding(ePath);
                encoding.encode(message, newPath);
            }
            case 2 -> {
                System.out.println("specify path for the input image");
                Scanner sc9 = new Scanner(System.in);
                String dPath = sc9.nextLine();
                Decoding decoding = new Decoding(dPath);
                String decodedMessage = decoding.decode();
                System.out.println(decodedMessage);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("welcome to the image steganography program");
        System.out.println("1. Encode");
        System.out.println("2. Decode");
        Scanner sc = new Scanner(System.in);
        var choice = sc.nextInt();
        Main.menu(choice);

    }
}

