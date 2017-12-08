package ru.korytnikov.oleg.atmdepartment;

import ru.korytnikov.oleg.Nominals;
import ru.korytnikov.oleg.State;

import java.util.Map;

public class Event {
    private Events event;
    private State state;

    Event(Events event) {
        this.event = event;
    }

    Event(Events event, State state) {
        this.event = event;
        this.state = state;
    }

    public State getState() {
        if (state != null) {
            return state;
        }
        return null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
}
