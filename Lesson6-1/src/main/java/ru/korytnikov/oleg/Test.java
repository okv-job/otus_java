package ru.korytnikov.oleg;

public class Test {

    public static void main (String ... args){
        ATM atm = ATM.getInstance();
        System.out.println("ADDING MONEY");
        atm.putMoney(0, new int[]{100,100,50});
        atm.checkBalance(0);

        System.out.println("GETTING MONEY");
        atm.getMoney(0,1050);
        atm.checkBalance(0);
    }
}
