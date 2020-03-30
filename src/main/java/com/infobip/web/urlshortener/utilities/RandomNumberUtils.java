package com.infobip.web.urlshortener.utilities;

public final class RandomNumberUtils {

    private RandomNumberUtils() {
    }

    public static int getRandomInteger(int min, int max) {
        return ((int) (Math.random() * (max - min))) + min;
    }
}
