package ru.korytnikov.oleg;

public class SecuritySystem {

    public static boolean validateUserAccessOrSomethingElse(int userId) {
        return true;
    }

    public static boolean validateCash(int sum) {
        return sum == 50 || sum == 100 || sum == 500 || sum == 1000;
    }

}
