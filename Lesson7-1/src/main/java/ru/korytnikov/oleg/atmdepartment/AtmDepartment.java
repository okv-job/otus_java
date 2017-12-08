package ru.korytnikov.oleg.atmdepartment;

import ru.korytnikov.oleg.State;
import ru.korytnikov.oleg.atm.ATM;

public interface AtmDepartment{
    void getBalanceFromAllAtm();
    void getBalanceFromAtm(ATM atm);
    void setInitialState(State state);
    void setInitialState();
    void register(Observer observer);
    void unregister(Observer observer);
}
