package ru.korytnikov.oleg;

public class Client {
    private long id;
    private String firstName;
    private String secondName;

    public Client(long id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public long getId() {
        return id;
    }
}
