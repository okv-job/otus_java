package ru.korytnikov.oleg.atmdepartment.events;

import ru.korytnikov.oleg.atm.ATM;

public interface Event {
    void execute(ATM atm);
}
