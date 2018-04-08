package ru.korytnikov.oleg.dao;

import ru.korytnikov.oleg.model.DataSet;

public interface Service {
    <T extends DataSet> void save(T user);
    <T extends DataSet> T load(long id, Class<T> clazz);
}
