package org.sopra.rogueguild.view.utils;

public final class Ansi {
    public static boolean enabled = true;

    public static final String R     = "\u001B[0m";
    public static final String GRAY  = "\u001B[90m";
    public static final String GREEN   = "\u001B[32m";
    public static final String RED   = "\u001B[31m";
    public static final String PURP  = "\u001B[35m";
    public static final String GOLD  = "\u001B[33m";
    public static final String CRIMSON  = "\u001B[91m";
    public static final String BLUE  = "\u001B[34m";
    

    public static String c(String color, String text) {
        if (!enabled) return text;
        return color + text + R;
    }

    private Ansi() {}
}

