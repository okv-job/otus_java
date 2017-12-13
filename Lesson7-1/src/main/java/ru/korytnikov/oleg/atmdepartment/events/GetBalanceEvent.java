package ru.korytnikov.oleg.atmdepartment.events;

import ru.korytnikov.oleg.atm.ATM;

public class GetBalanceEvent implements Event{

    @Override
    public void execute(ATM atm) {
        atm.getATMCash();
    }
}
