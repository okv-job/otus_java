package ru.korytnikov.oleg;

import ru.korytnikov.oleg.atm.ATM;
import ru.korytnikov.oleg.atmdepartment.AtmDepartment;
import ru.korytnikov.oleg.atmdepartment.AtmDepartmentImpl;

import java.util.HashMap;
import java.util.Map;

public class Test {

    private static Map<Nominals, Integer> mapState1 = new HashMap<>();
    private static Map<Nominals, Integer> mapState2 = new HashMap<>();


    static {
        mapState1.put(Nominals.rub50, 10);
        mapState1.put(Nominals.rub100, 10);
        mapState1.put(Nominals.rub500, 10);
        mapState1.put(Nominals.rub1000, 10);

        mapState2.put(Nominals.rub50, 5);
        mapState2.put(Nominals.rub100, 5);
        mapState2.put(Nominals.rub500, 5);
        mapState2.put(Nominals.rub1000, 5);
    }

    public static void main (String ... args) {
        AtmDepartment atmDepartment = new AtmDepartmentImpl();

        State state1 = new State(mapState1);
        State state2 = new State(mapState2);

        ATM atm1 = new ATM(state1);
        ATM atm2 = new ATM(state2);

        atmDepartment.register(atm1);
        atmDepartment.register(atm2);

        atmDepartment.getBalanceFromAllAtm();

        atm1.getMoney(0, 300);
        atmDepartment.getBalanceFromAtm(atm1);
        atmDepartment.setInitialState();
        atmDepartment.getBalanceFromAllAtm();
    }
}
