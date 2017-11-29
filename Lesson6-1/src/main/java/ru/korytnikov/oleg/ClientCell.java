package ru.korytnikov.oleg;

import ru.korytnikov.oleg.money.Dollar;
import ru.korytnikov.oleg.money.Euro;
import ru.korytnikov.oleg.money.Money;
import ru.korytnikov.oleg.money.Ruble;

public class ClientCell {

    private Dollar dollars;
    private Euro euros;
    private Ruble rubles;

    public String getBalance(){
        return "Dollars = " + dollars.getBalance() +
                " Euros = "  + euros + " Rubles = " + rubles.getBalance();
    }

    public void put (Money money){
        if (money instanceof Dollar) {
            if (dollars == null) {
                dollars = new Dollar();
            }
            dollars.addCash(money);
        }
    }

}
