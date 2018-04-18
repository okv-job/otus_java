package ru.korytnikov.oleg;

import ru.korytnikov.oleg.dao.DBService;
import ru.korytnikov.oleg.dao.hibernate.DBServiceHybernateImpl;
import ru.korytnikov.oleg.model.UserDataSet;

public class Main {
    public static void main(String[] args) {
        UserDataSet dataSet = new UserDataSet("oleg", 22, "Kalinina");
        DBService service = new DBServiceHybernateImpl();
        service.save(dataSet);
        UserDataSet nDa = service.load(1, UserDataSet.class);
        System.out.println(nDa);
        service.shutdown();
    }
}
