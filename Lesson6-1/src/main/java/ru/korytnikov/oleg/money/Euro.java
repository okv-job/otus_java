package ru.korytnikov.oleg.money;

public class Euro implements Money{

    private static final String CURRENCY = "EURO";

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
