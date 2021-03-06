package ru.korytnikov.oleg.atmdepartment;

import ru.korytnikov.oleg.State;
import ru.korytnikov.oleg.atm.ATM;
import ru.korytnikov.oleg.atmdepartment.events.GetBalanceEvent;
import ru.korytnikov.oleg.atmdepartment.events.SetInitialStateEvent;

import java.util.ArrayList;
import java.util.List;

public class AtmDepartmentImpl implements AtmDepartment {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void getBalanceFromAllAtm() {
        observers.forEach(observer -> observer.notify(new GetBalanceEvent()));
    }

    @Override
    public void getBalanceFromAtm(ATM atm) {
        atm.notify(new GetBalanceEvent());
    }

    @Override
    public void setInitialState(State state) {
        observers.forEach(observer -> observer.notify(new SetInitialStateEvent(state)));
    }

    @Override
    public void setInitialState() {
        observers.forEach(observer -> observer.notify(new SetInitialStateEvent()));
    }


    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }
}
