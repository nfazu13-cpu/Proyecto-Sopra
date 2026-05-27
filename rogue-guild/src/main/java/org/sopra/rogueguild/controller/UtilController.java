package org.sopra.rogueguild.controller;

import java.util.Scanner;

public class UtilController {
    
    protected final Scanner sc = new Scanner(System.in);

    public int askForInt() {
        if (sc.hasNextInt()) {
            int number = sc.nextInt();
            cleanBuffer();
            return number;
        } else { 
            cleanBuffer();
            return -1;
        }
    }

    public void cleanBuffer() {
        sc.nextLine();
    }
}