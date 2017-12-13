package ru.korytnikov.oleg.atmdepartment.events;

import ru.korytnikov.oleg.State;
import ru.korytnikov.oleg.atm.ATM;

public class SetInitialStateEvent implements Event {

    private State state;

    public SetInitialStateEvent(){

    }

    public SetInitialStateEvent(State state){
        this.state = state;
    }

    @Override
    public void execute(ATM atm) {
        if (state == null){
            atm.setInitialState();
        } else {
            atm.setInitialState(state);
        }
    }
}
