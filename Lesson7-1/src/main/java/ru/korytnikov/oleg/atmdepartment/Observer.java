package ru.korytnikov.oleg.atmdepartment;

import ru.korytnikov.oleg.atmdepartment.events.Event;

public interface Observer {
    void notify(Event event);
}
