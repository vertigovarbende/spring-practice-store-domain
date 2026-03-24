package com.deveyk.bookstore.common.util;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.UUID;

@UtilityClass
public class BsRandomUtil {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static Long generateRandomLong(int length) {
        long min = generateMinimumValue(length);
        long max = generateMaximumValue(length);
        return SECURE_RANDOM.nextLong(min, max);
    }

    public static Integer generateRandomInteger(int length) {
        int min = generateMinimumValue(length).intValue();
        int max = generateMaximumValue(length).intValue();
        return SECURE_RANDOM.nextInt(min, max);
    }

    private static Long generateMinimumValue(int length) {
        return (long) Math.pow(10.0, length - 1.0);
    }

    private static Long generateMaximumValue(int length) {
        return (long) Math.pow(10.0, length);
    }



}
