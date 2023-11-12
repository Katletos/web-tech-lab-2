package by.bsuir.dao.interfaces;

import by.bsuir.dao.DaoException;
import by.bsuir.domain.entities.User;

public interface UserDAO {
    User getUserById(Long userId) throws DaoException;
    Boolean addUser(User user) throws DaoException;
    void removeUserById(Long userId);
}
