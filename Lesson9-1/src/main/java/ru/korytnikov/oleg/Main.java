package ru.korytnikov.oleg;

import ru.korytnikov.oleg.dao.hibernate.DBServiceHybernateImpl;
import ru.korytnikov.oleg.model.UserDataSet;
import ru.korytnikov.oleg.webserver.ServerImpl;
import ru.korytnikov.oleg.webserver.model.StatusInfo;

public class Main {
    private static DBServiceHybernateImpl service;
    private static StatusInfo statusInfo;

    public static void main(String[] args) throws Exception {
//        runHibernateImpl();
        runJettyImpl();
    }

    private static void loadDataset(int id, String name) {
        System.out.println("Load " + name +  " dataset which saved in cache ");
        UserDataSet data = service.load(id, UserDataSet.class);
        System.out.println(data);
        System.out.println("Cache stat: HITS: " + service.getCacheEngine().getHitCount() + " MISS: "
                + service.getCacheEngine().getMissCount());
    }

    private static void runHibernateImpl(){
        service = new DBServiceHybernateImpl();

        UserDataSet oleg = new UserDataSet("oleg", 22, "Kalinina");
        UserDataSet sasha = new UserDataSet("Sasha", 47, "Kalinina", 2);

        service.save(oleg);
        service.save(sasha);

        loadDataset(1, "Oleg");
        loadDataset(2, "Sasha");

        service.shutdown();
    }

    private static void runHibernateImpl(StatusInfo statusInfo){
        service = new DBServiceHybernateImpl(statusInfo);

        UserDataSet oleg = new UserDataSet("oleg", 22, "Kalinina");
        UserDataSet sasha = new UserDataSet("Sasha", 47, "Kalinina", 2);

        service.save(oleg);
        service.save(sasha);

        loadDataset(1, "Oleg");
        loadDataset(2, "Sasha");

        service.shutdown();
    }

    private static void runJettyImpl() throws Exception {
        statusInfo = new StatusInfo(10, true);
        runHibernateImpl(statusInfo);
        new ServerImpl();
    }

    public synchronized static StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public synchronized static DBServiceHybernateImpl getService() {
        return service;
    }
}
