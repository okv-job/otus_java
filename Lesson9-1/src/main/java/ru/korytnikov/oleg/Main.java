package ru.korytnikov.oleg;

import ru.korytnikov.oleg.dao.hibernate.DBServiceHybernateImpl;
import ru.korytnikov.oleg.model.UserDataSet;

public class Main {
    public static void main(String[] args) {
        DBServiceHybernateImpl service = new DBServiceHybernateImpl();

        UserDataSet oleg = new UserDataSet("oleg", 22, "Kalinina");
        UserDataSet sasha = new UserDataSet("Sasha", 47, "Kalinina", 2);

        service.save(oleg);
        service.save(sasha);

        System.out.println("Load Oleg dataset which dont saved in cache ");
        UserDataSet loadOleg1 = service.load(1, UserDataSet.class);
        System.out.println(loadOleg1);
        System.out.println("Cache stat: HITS: " + service.getCacheEngine().getHitCount() + " MISS: "
                + service.getCacheEngine().getMissCount());
        System.out.println("Load Oleg dataset which saved in cache ");
        UserDataSet loadOleg2 = service.load(1, UserDataSet.class);
        System.out.println(loadOleg2);
        System.out.println("Cache stat: HITS: " + service.getCacheEngine().getHitCount() + " MISS: "
                + service.getCacheEngine().getMissCount());
        System.out.println("Load Sasha dataset which saved in cache ");
        UserDataSet loadSasha = service.load(2, UserDataSet.class);
        System.out.println(loadSasha);
        System.out.println("Cache stat: HITS: " + service.getCacheEngine().getHitCount() + " MISS: "
                + service.getCacheEngine().getMissCount());
        service.shutdown();
    }
}
