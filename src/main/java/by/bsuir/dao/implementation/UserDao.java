package by.bsuir.dao.implementation;

import by.bsuir.dao.DaoException;
import by.bsuir.dao.connectionpool.ConnectionPool;
import by.bsuir.dao.connectionpool.ConnectionPoolException;
import by.bsuir.dao.interfaces.UserDAO;
import by.bsuir.domain.entities.User;
import by.bsuir.domain.enums.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements UserDAO {
    @Override
    public User getUserById(Long userId) throws DaoException {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            var connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(
                    "SELECT id, first_name, last_name, patronymic, email, password_hash, role FROM users WHERE id = ?");
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setPatronymic(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                user.setPasswordHash(resultSet.getString(6));
                user.setRole(UserRole.valueOf(resultSet.getString(7)));
            };

            return user;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Can't get user by id", e);
        }
    }

    @Override
    public Boolean addUser(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            var connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement("INSERT INTO users " +
                    "(first_name, last_name, patronymic, email, password_hash, role) " +
                    "VALUES (?, ?, ?, ?, ?, CAST(? AS user_role))");

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPatronymic());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPasswordHash());
            statement.setString(6, user.getRole().toString());

            return statement.execute();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("asd", e);
        } finally {
            try {
                if (statement != null)
                {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DaoException("asd", e);
            }
        }
    }

    @Override
    public void removeUserById(Long userId) {

    }

    //получить все препараты
    //добавить заказ
    //добавить/удалить препарат
    //назначить рецепт
    //отправить запрос на пробдление рецепта
}
