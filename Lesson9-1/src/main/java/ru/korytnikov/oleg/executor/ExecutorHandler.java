package ru.korytnikov.oleg.executor;

import java.sql.ResultSet;

@FunctionalInterface
public interface ExecutorHandler<T> {
    T handle(ResultSet result) throws InstantiationException;
}
