package ru.korytnikov.oleg.atm;

import ru.korytnikov.oleg.atm.Errors.ClientNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class BankStore {

    private static BankStore instance;
    private static Map<Integer, Client> clients = new HashMap<>();

    static {
        clients.put(0, new Client(0, "okv", 1000));
        clients.put(1, new Client(1, "test", 0));
    }

    private BankStore() {
    }

    public static BankStore getInstance() {
        if (instance == null) {
            instance = new BankStore();
        }
        return instance;
    }

    public int getClientBalance(int id) throws ClientNotFoundException {
        return getClient(id).getCashInformation();
    }

    public void addCasHToClientBalance(int id, int sum) throws ClientNotFoundException {
        getClient(id).addCash(sum);
    }

    public boolean getCashFromClientBalance(int id, int sum) throws ClientNotFoundException {
        return getClient(id).getCash(sum);
    }


    private boolean isClientExists(int id) {
        return clients.containsKey(id);
    }

    private Client getClient(int id) throws ClientNotFoundException {
        if (isClientExists(id)) {
            return clients.get(id);
        } else throw new ClientNotFoundException();
    }
}