package ru.korytnikov.oleg.money;

public interface Money {
    String getCurrency();
    void addCash(Money money);
    int getBalance();
    boolean getCash();
}
