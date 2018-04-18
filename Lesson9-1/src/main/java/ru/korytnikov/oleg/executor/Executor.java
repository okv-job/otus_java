package ru.korytnikov.oleg.executor;

import ru.korytnikov.oleg.dao.orm.DbConnector;

import java.sql.*;

public class Executor {

    private Connection connection;

    public Executor() {
        connection = DbConnector.getConnection();
    }

    public int execUpdate(String update) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(update)) {
            stmt.execute();
            return stmt.getUpdateCount();
        }
    }

    public <T> T execQuery(String query, ExecutorHandler<T> handler) throws SQLException, InstantiationException {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();
        return value;
    }
}
