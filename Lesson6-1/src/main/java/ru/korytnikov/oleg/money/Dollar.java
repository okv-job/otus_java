package ru.korytnikov.oleg.money;

public class Dollar implements Money{

    private static final String CURRENCY = "DOLLAR";
    private long currentValue = 0;

    @Override
    public String getCurrency() {
        return CURRENCY;
    }

    @Override
    public void addCash(Money money) {
        ;
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
