package ru.korytnikov.oleg.money;

public class Dollar implements Money{

    private static final String CURRENCY = "DOLLAR";

    @Override
    public String getCurrency() {
        return null;
    }

    @Override
    public void addCash(Money money) {

    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public boolean getCash() {
        return false;
    }
}
