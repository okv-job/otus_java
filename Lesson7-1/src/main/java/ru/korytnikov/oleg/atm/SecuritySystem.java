package ru.korytnikov.oleg.atm;

import ru.korytnikov.oleg.Nominals;

import java.util.Map;

public class SecuritySystem {

    public static boolean validateUserAccessOrSomethingElse(int userId) {
        return true;
    }

    public static boolean validateCash(int sum) {
        for (Map.Entry<Nominals,Integer> entry: ATM.nominalMap.entrySet()) {
            if (entry.getValue() == sum) {
                return true;
            }
        }
        return false;
    }

}
