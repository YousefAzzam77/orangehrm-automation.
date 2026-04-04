package utils;

import java.util.UUID;

public class DataGenerator {

    public static String generateUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0,5);
    }
}