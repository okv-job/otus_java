package ru.korytnikov.oleg.atm;

import ru.korytnikov.oleg.State;

public class Memento {
    private final State state;

    public Memento(State stateToSave) {
        state = stateToSave;
    }

    public State getSavedState() {
        return state;
    }
}
