package ru.korytnikov.oleg.Errors;

public class ClientNotFoundException extends Exception {

    private static final String MESSAGE ="Client not found";

    public ClientNotFoundException(){
        super(MESSAGE);
    }
}
