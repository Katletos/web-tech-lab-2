package by.bsuir.application.interfaces;

import by.bsuir.application.dtos.UpdateUserDto;
import by.bsuir.application.dtos.UserDto;

public interface UserService {
    Boolean loginUser(String email, String passwordHash);

    UserDto registerUser(UpdateUserDto dto);
}
