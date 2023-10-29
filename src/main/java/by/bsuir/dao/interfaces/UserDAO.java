package by.bsuir.dao.interfaces;

import by.bsuir.domain.entities.User;

public interface UserDAO {
    Long addUser(User user);
    void removeUserById(Long userId);
}
