package ru.korytnikov.oleg;

import java.util.HashMap;
import java.util.Map;

public class State {
    private Map<Nominals,Integer> state = new HashMap<>();

    public State(Map<Nominals, Integer> state) {
        this.state = state;
    }

    public void setRubles(Nominals nominal, int sum){
        state.put(nominal, sum);
    }

    public Map<Nominals, Integer> getState() {
        return state;
    }

    public void setState(Map<Nominals, Integer> state) {
        this.state = state;
    }
}
