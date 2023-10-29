package by.bsuir;

import by.bsuir.dao.connectionpool.ConnectionPool;
import by.bsuir.dao.connectionpool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            connection = new ConnectionPool().takeConnection();
            System.out.println(connection.isClosed());
            connection.close();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }
}