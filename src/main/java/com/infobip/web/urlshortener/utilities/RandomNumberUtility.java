package com.infobip.web.urlshortener.utilities;

public class RandomNumberUtility {
    public static int getRandomInteger(int min, int max) {
        return ((int) (Math.random() * (max - min))) + min;
    }
}
