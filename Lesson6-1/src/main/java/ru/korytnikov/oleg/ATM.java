package ru.korytnikov.oleg;

public class ATM {
    private static ATM instance;

    private ATM() {
    }

    private static ATM getInstance() {
        if (instance == null) {
            instance = new ATM();
        }
        return instance;
    }
}
