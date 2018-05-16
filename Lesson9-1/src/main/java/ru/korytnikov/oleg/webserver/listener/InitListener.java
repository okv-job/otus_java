package ru.korytnikov.oleg.webserver.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.korytnikov.oleg.dao.hibernate.DBServiceHybernateImpl;
import ru.korytnikov.oleg.model.UserDataSet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

    private DBServiceHybernateImpl serviceHybernate;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initialize listener");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        serviceHybernate = (DBServiceHybernateImpl) ctx.getBean("hibernateService");

        UserDataSet oleg = new UserDataSet("oleg", 22, "Kalinina");
        UserDataSet sasha = new UserDataSet("Sasha", 47, "Kalinina", 2);

        serviceHybernate.save(oleg);
        serviceHybernate.save(sasha);

        loadDataset(1, "Oleg");
        loadDataset(2, "Sasha");


        sce.getServletContext().setAttribute("hibernateService", serviceHybernate);
        sce.getServletContext().setAttribute("AppCtx", ctx);

    }

    private void loadDataset(int id, String name) {
        System.out.println("Load " + name +  " dataset which saved in cache ");
        UserDataSet data = serviceHybernate.load(id, UserDataSet.class);
        System.out.println(data);
        System.out.println("Cache stat: HITS: " + serviceHybernate.getCacheEngine().getHitCount() + " MISS: "
                + serviceHybernate.getCacheEngine().getMissCount());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (serviceHybernate != null) serviceHybernate.shutdown();
    }
}
