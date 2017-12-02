package ru.korytnikov.oleg;

public class Test {

    public static void main (String ... args) {
        ATM atm = new ATM();
        atm.getATMCash();


        System.out.println("ADDING MONEY");
        atm.putMoney(0, new int[]{100,100,50});
        atm.checkBalance(0);

        atm.getATMCash();

        System.out.println("GETTING MONEY");
        atm.getMoney(0,350);
        atm.checkBalance(0);

        atm.getATMCash();

        atm.getMoney(1, 100);
    }
}
