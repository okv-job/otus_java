package ru.korytnikov.oleg.dao;

import ru.korytnikov.oleg.model.DataSet;

public interface DBService {
    <T extends DataSet> void save(T dataSet);
    <T extends DataSet> T load(long id, Class<T> clazz);
    void shutdown();
}
