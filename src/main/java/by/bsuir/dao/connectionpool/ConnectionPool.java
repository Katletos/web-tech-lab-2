package by.bsuir.dao.connectionpool;

import java.sql.*;
import java.util.Locale;


public class ConnectionPool {
    private static Connection connection;

    public static synchronized Connection getConnection() throws ConnectionPoolException {
        if (connection == null) {
            try {
                Locale.setDefault(Locale.ENGLISH);
                Class.forName(DBParameter.DB_DRIVER);
                connection = DriverManager.getConnection(
                        DBParameter.DB_URL,
                        DBParameter.DB_USER,
                        DBParameter.DB_PASSWORD);
            } catch (SQLException e) {
                throw new ConnectionPoolException("SQLException in ConnectionPool", e);
            } catch (ClassNotFoundException e) {
                throw new ConnectionPoolException("Can't find database driver class", e);
            }
        }

        return connection;
    }
}