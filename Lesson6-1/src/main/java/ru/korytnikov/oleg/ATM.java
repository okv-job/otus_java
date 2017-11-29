package ru.korytnikov.oleg;

public class ATM {
    private static ATM instance;
    private static BankStore store = BankStore.getInstance();

    private ATM() {
    }

    public static ATM getInstance() {
        if (instance == null) {
            instance = new ATM();
        }
        return instance;
    }

    public void checkBalance(Client client) {
        store.getClientBalance(client);
    }
}
