package by.bsuir;

import by.bsuir.dao.connectionpool.ConnectionPool;
import by.bsuir.dao.connectionpool.ConnectionPoolException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            var connection = ConnectionPool.getConnection();
            System.out.println(connection.isClosed());
            connection.close();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }
}