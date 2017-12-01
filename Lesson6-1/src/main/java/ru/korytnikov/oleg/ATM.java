package ru.korytnikov.oleg;

import ru.korytnikov.oleg.Errors.ClientNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ATM {
    private static ATM instance;
    private static BankStore store = BankStore.getInstance();
    private static int[] nominal = {50, 100, 500, 1000};

    //Номинал, Количество банкнот
    private static Map<Integer, Integer> ATMCashStorage = new HashMap<>();


    static {
        ATMCashStorage.put(nominal[0], 10);
        ATMCashStorage.put(nominal[1], 10);
        ATMCashStorage.put(nominal[2], 10);
        ATMCashStorage.put(nominal[3], 10);
    }

    private ATM() {
    }

    public static ATM getInstance() {
        if (instance == null) {
            instance = new ATM();
        }
        return instance;
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

        try {
            if (store.getClientBalance(userId) < sum) {
                System.out.println("Недостаточно средств");
                return;
            }
        } catch (ClientNotFoundException clientNotFound) {
            System.out.println(clientNotFound.getMessage());
            return;
        }

        boolean isFinished = false;

        for (int i = nominal.length - 1; i >= 0; i--){
            int curVal = nominal[i];
            int val = ATMCashStorage.get(curVal);
            if (curVal > sum) {
                continue;
            }
            else if (sum % curVal == 0) {
                if (val >= sum/curVal) {
                    ATMCashStorage.put(curVal, val - sum/curVal);
                    System.out.println("Take your money");
                    isFinished = true;
                }
            }
            else {
                for (int j = 1; j < val; j++){
                    sum -= curVal;
                    if (sum == 0) {
                        ATMCashStorage.put(curVal, val - j);
                        System.out.println("Take your money");
                        isFinished = true;
                    }
                    else if (sum < 0) {
                        ATMCashStorage.put(curVal, val - j + 1);
                        sum += curVal;
                        break;
                    }
                }
            }
        }

        if(!isFinished) System.out.println("Запрошенная сумма не может быть выдана");

    }

    public void putMoney(int userId, int[] sum) {
        if (!SecuritySystem.validateUserAccessOrSomethingElse(userId)) return;

        int incomingSum = 0;

        for (int i : sum) {
            if (!SecuritySystem.validateCash(i)) {
                System.out.println("Принимаются купюры наминалом 50, 100, 500, 1000");
                return;
            }
            incomingSum += i;
        }

        try {
            store.addCasHToClientBalance(userId, incomingSum);
        } catch (ClientNotFoundException clientNotFound) {
            System.out.println(clientNotFound.getMessage());
        }
    }

    private static boolean ATMHasCash(int sum) {
        int money = 0;
        for (Map.Entry<Integer, Integer> entry : ATMCashStorage.entrySet()) {
            money += entry.getKey() * entry.getValue();
        }
        return money >= sum;
    }

}
