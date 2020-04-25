package com.infobip.web.urlshortener.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomNumberUtilities {
    public static int getRandomInteger(int min, int max) {
        return ((int) (Math.random() * (max - min))) + min;
    }
}
