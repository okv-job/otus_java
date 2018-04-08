package ru.korytnikov.oleg;

import ru.korytnikov.oleg.dao.DbService;
import ru.korytnikov.oleg.model.UserDataSet;

public class Main {
    public static void main(String[] args) {
        UserDataSet dataSet = new UserDataSet("oleg", 22, 1);
        DbService service = new DbService();
        service.save(dataSet);
        UserDataSet nDa = service.load(1, UserDataSet.class);
        System.out.println(nDa);
    }
}
