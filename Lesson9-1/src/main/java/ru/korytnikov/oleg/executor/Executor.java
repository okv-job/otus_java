package ru.korytnikov.oleg.executor;

import ru.korytnikov.oleg.dao.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

    private Connection connection;

    public Executor() {
        connection = DbConnector.getConnection();
    }

    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            return stmt.getUpdateCount();
        }
    }

    public <T> T execQuery(String query, ExecutorHandler<T> handler) throws SQLException, InstantiationException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();
        return value;
    }
}
