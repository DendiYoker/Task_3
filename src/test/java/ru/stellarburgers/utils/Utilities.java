package ru.stellarburgers.utils;

import com.github.javafaker.Faker;
import ru.stellarburgers.dto.CreatedUserData;

import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Utilities {

    private static final Faker FAKER = new Faker(new Locale("ru"));
    private static final Faker FAKER_EN = new Faker();

    public static String generateUniqueName() {
        return FAKER_EN.name().username().toLowerCase() +
                ThreadLocalRandom.current().nextInt(1000, 9999);
    }

    public static String generateCorrectPassword(){
        return UUID.randomUUID().toString().substring(0, 6);
    }

    public static String generateincorrectPassword(){
        return UUID.randomUUID().toString().substring(0, 4);
    }

    public static String generateRandomEmail(){
        return FAKER_EN.internet().emailAddress();
    }

    public static String generateRandomHash() {
        return UUID.randomUUID().toString();
    }

    public static CreatedUserData createRandomUser() {
        return new CreatedUserData(generateRandomEmail(), generateCorrectPassword(), generateUniqueName());
    }

    public static CreatedUserData createRandomUserWithIncorrectPassword() {
        return new CreatedUserData(generateRandomEmail(), generateincorrectPassword(), generateUniqueName());
    }

    public static void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        }
    }

}
