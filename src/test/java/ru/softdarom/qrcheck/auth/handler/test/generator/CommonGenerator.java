package ru.softdarom.qrcheck.auth.handler.test.generator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonGenerator {

    private static final int DEFAULT_WORD_LENGTH = 20;

    public static Long generateLong() {
        var leftLimit = 1L;
        var rightLimit = Long.MAX_VALUE;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public static String generateString() {
        return generateString(DEFAULT_WORD_LENGTH);
    }

    public static String generateString(long length) {
        var leftLimit = 48;
        var rightLimit = 122;

        return new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> i <= 57 || i >= 65)
                .filter(i -> i <= 90 || i >= 97)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static Integer generateInteger() {
        return Math.abs(new Random().nextInt());
    }

    public static Integer generateInteger(Integer min, Integer max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
}
