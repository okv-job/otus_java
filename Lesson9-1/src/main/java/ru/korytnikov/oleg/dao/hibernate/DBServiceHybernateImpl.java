package ru.korytnikov.oleg.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.korytnikov.oleg.cache.CacheEngineImpl;
import ru.korytnikov.oleg.cache.MyElement;
import ru.korytnikov.oleg.dao.DBService;
import ru.korytnikov.oleg.model.AddressDataSet;
import ru.korytnikov.oleg.model.DataSet;
import ru.korytnikov.oleg.model.UserDataSet;

import java.util.function.Function;

public class DBServiceHybernateImpl implements DBService {

    private final SessionFactory sessionFactory;
    private CacheEngineImpl<Long, DataSet> cacheEngine;

    public DBServiceHybernateImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/Test");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "12345");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
        cacheEngine = new CacheEngineImpl<>(10, 0, 0, true);
    }

    public DBServiceHybernateImpl(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
    }

    @Override
    public <T extends DataSet> void save(T dataSet) {
        runInSession(session -> {
            if (dataSet.getId() != 0) {
                cacheEngine.put(new MyElement<>(dataSet.getId(), dataSet));
            }
            session.save(dataSet);
            return dataSet;
        });
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            T dataSet;
            MyElement<Long, DataSet> element = cacheEngine.get(id);
            if (element == null) {
                dataSet = session.load(clazz, id);
                cacheEngine.put(new MyElement<>(id, dataSet));
                return dataSet;
            }
            dataSet = (T) element.getValue();
            return dataSet;
        }
    }

    public CacheEngineImpl<Long, DataSet> getCacheEngine() {
        return cacheEngine;
    }
}
