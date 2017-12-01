package ru.korytnikov.oleg;

public class Client {
    private int id;
    private String name;
    private int cash;

    public Client(int id, String name, int cash) {
        this.id = id;
        this.name = name;
        this.cash = cash;
    }

    public void addCash (int sum) {
        cash += sum;
    }

    public boolean getCash(int sum){
        if (cash - sum >= 0) {
            cash -= sum;
            return true;
        }
        return false;
    }

    public int getCashInformation(){
        return cash;
    }

}
