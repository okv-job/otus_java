package ru.korytnikov.oleg.dao;

import ru.korytnikov.oleg.executor.Executor;
import ru.korytnikov.oleg.model.DataSet;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlDbService implements DBService {


    private List<Option> getOptions(Field[] fields, Object obj) {
        List<Option> options = new ArrayList<>();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object val = field.get(obj);
                Option option = new Option(field.getType(), field.getName(), val);
                options.add(option);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return options;
    }

    private List<Option> loadOptions(Class aClass, Object object) {
        List<Option> options = new ArrayList<>();

        Class tmpClass = aClass.getSuperclass();
        while (tmpClass != null) {
            Field[] fields = tmpClass.getDeclaredFields();
            options.addAll(getOptions(fields, object));
            tmpClass = tmpClass.getSuperclass();
        }

        Field[] fields = aClass.getDeclaredFields();
        options.addAll(getOptions(fields, object));
        return options;
    }

    private boolean hasId(List<Option> options) {
        for (Option option : options) {
            if (option.getName().equals("id")) {
                return true;
            }
        }
        return false;
    }

    private String getInsertQuery(List<Option> options, Class clazz) {
        StringBuilder insertQuery = new StringBuilder();
        insertQuery.append("INSERT INTO ").append(clazz.getSimpleName()).append(" VALUES(");
        if (!hasId(options)) {
            insertQuery.append("NULL, ");
        }
        for (int i = 0; i < options.size(); i++) {
            if (i != options.size() - 1) {
                if (String.class.equals(options.get(i).getType())) {
                    insertQuery.append("'").append(options.get(i).getValue()).append("', ");
                } else {
                    insertQuery.append(options.get(i).getValue()).append(", ");
                }
            } else {
                if (String.class.equals(options.get(i).getType())) {
                    insertQuery.append("'").append(options.get(i).getValue()).append("')");
                } else {
                    insertQuery.append(options.get(i).getValue()).append(")");
                }
            }
        }
        return insertQuery.toString();
    }


    @Override
    public <T extends DataSet> void save(T user) {
        Executor executor = new Executor();
        Class aClass = user.getClass();
        List<Option> options = loadOptions(aClass, user);
        try {
            executor.execUpdate(getInsertQuery(options, aClass));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        Executor executor = new Executor();
        T obj = null;
        try {
            obj = executor.execQuery("SELECT * FROM " + clazz.getSimpleName() + " WHERE id = " + id, result -> {
                T object = null;
                try {
                    if (result.next()) {
                        object = clazz.newInstance();
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            try {
                                field.setAccessible(true);
                                field.set(object, result.getObject(field.getName()));
                            } catch (IllegalAccessException | SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (IllegalAccessException | SQLException e) {
                    e.printStackTrace();
                }
                if (object != null) {
                    object.setId(id);
                }
                return object;
            });
        } catch (SQLException | InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void shutdown() {
        try {
            DbConnector.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
