package ru.korytnikov.oleg;

import ru.korytnikov.oleg.money.Dollar;

import java.util.HashMap;
import java.util.Map;

public class BankStore {

    private static BankStore instance;
    private static Map<Long, ClientCell> store = new HashMap<>();

    static {
        ClientCell clientCell = new ClientCell();
        clientCell.put(new Dollar(100));
        store.put(1L,clientCell);
    }

    private BankStore() {
    }

    public static BankStore getInstance() {
        if (instance == null) {
            instance = new BankStore();
        }
        return instance;
    }

    public void getClientBalance(Client client) {
        if (store.containsKey(client.getId())){
            store.get(client.getId()).getBalance();
        }
        else {
            System.out.println("Client not found");
        }
    }

}
