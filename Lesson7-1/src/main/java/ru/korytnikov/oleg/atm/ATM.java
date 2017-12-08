package ru.korytnikov.oleg.atm;

import ru.korytnikov.oleg.State;
import ru.korytnikov.oleg.atm.Errors.ClientNotFoundException;
import ru.korytnikov.oleg.Nominals;
import ru.korytnikov.oleg.atmdepartment.Event;
import ru.korytnikov.oleg.atmdepartment.Observer;

import java.util.*;
import java.util.stream.IntStream;

public class ATM implements Observer {
    private static BankStore store = BankStore.getInstance();
    static Map<Nominals, Integer> nominalMap = new LinkedHashMap<>();

    //Номинал, Количество банкнот
    private Map<Nominals, Integer> ATMCashStorage;
    private List<Memento> states = new ArrayList<>();

    static {
        nominalMap.put(Nominals.rub1000, 1000);
        nominalMap.put(Nominals.rub500, 500);
        nominalMap.put(Nominals.rub100, 100);
        nominalMap.put(Nominals.rub50, 50);
    }

    public ATM(State state) {
        ATMCashStorage = new HashMap<>(state.getState());
        states.add(new Memento(state));
    }

    public void checkBalance(int userId) {
        if (!SecuritySystem.validateUserAccessOrSomethingElse(userId)) return;
        try {
            System.out.println(store.getClientBalance(userId));
        } catch (ClientNotFoundException clientNotFound) {
            System.out.println(clientNotFound.getMessage());
        }
    }

    public void getMoney(int userId, int sum) {

        if (!SecuritySystem.validateUserAccessOrSomethingElse(userId)) return;

        if (!ATMHasCash(IntStream.of(sum).sum())) {
            System.out.println("Dont have enough money!");
            return;
        }

        boolean isFinished = false;
        int tempSum = sum;
        Map<Nominals, Integer> tempATMCashStorage = new HashMap<>(ATMCashStorage);

        for (Map.Entry<Nominals, Integer> entry : nominalMap.entrySet()) {
            int curVal = entry.getValue();
            int val = tempATMCashStorage.get(entry.getKey());
            if (curVal <= tempSum) {
                if (tempSum % curVal == 0) {
                    if (val >= tempSum / curVal) {
                        tempATMCashStorage.put(entry.getKey(), val - tempSum / curVal);
                        isFinished = true;
                        break;
                    }
                } else {
                    for (int j = 1; j < val; j++) {
                        tempSum -= curVal;
                        if (tempSum == 0) {
                            tempATMCashStorage.put(entry.getKey(), val - j);
                            isFinished = true;
                            break;
                        } else if (tempSum < 0) {
                            tempATMCashStorage.put(entry.getKey(), val - j + 1);
                            tempSum += curVal;
                            break;
                        }
                    }
                }
            }
        }

        if (!isFinished) System.out.println("Запрошенная сумма не может быть выдана");

        else try {
            if (store.getCashFromClientBalance(userId, sum)) {
                System.out.println("Take your money");
                ATMCashStorage = new HashMap<>(tempATMCashStorage);
            } else System.out.println("Запрошенная сумма не может быть выдана");
        } catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void putMoney(int userId, int[] sum) {
        if (!SecuritySystem.validateUserAccessOrSomethingElse(userId)) return;

        int incomingSum = 0;

        for (int i : sum) {
            if (!SecuritySystem.validateCash(i)) {
                System.out.println("Неверный номинал!");
                return;
            }
            incomingSum += i;
        }

        try {
            store.addCasHToClientBalance(userId, incomingSum);

            for (int i : sum) {
                Nominals key = getKeyByValue(nominalMap, i);
                ATMCashStorage.put(key, ATMCashStorage.get(key) + 1);
            }

        } catch (ClientNotFoundException clientNotFound) {
            System.out.println(clientNotFound.getMessage());
        }
    }

    private boolean ATMHasCash(int sum) {
        return ATMCash() >= sum;
    }

    private int ATMCash() {
        int money = 0;
        for (Map.Entry<Nominals, Integer> entry : ATMCashStorage.entrySet()) {
            money += nominalMap.get(entry.getKey()) * entry.getValue();
        }
        return money;
    }

    public void getATMCash() {
        System.out.println("Банконты : ");
        for (Map.Entry<Nominals, Integer> entry : ATMCashStorage.entrySet()) {
            System.out.println("Номинал " + entry.getKey() + " количество " + entry.getValue());
        }
        System.out.println("Итоговый остаток " + ATMCash());

    }

    private <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public void notify(Event event) {
        switch (event.getEvent()) {
            case getBalance:
                getATMCash();
                break;
            case setInitialState: {
                if (event.getState() == null) {
                    ATMCashStorage = new HashMap<>(getLastState());
                    System.out.println("Done");
                    break;

                }
                states.add(new Memento(event.getState()));
                ATMCashStorage = new HashMap<>(getLastState());
                System.out.println("Done");
                break;
            }
        }
    }

    private Map<Nominals,Integer> getLastState(){
        State savedState = states.get(states.size()-1).getSavedState();
        return savedState.getState();
    }
}
