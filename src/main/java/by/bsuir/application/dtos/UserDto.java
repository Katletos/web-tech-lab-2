package by.bsuir.application.dtos;

import by.bsuir.domain.enums.UserRole;

public class UserDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String patronymic;
    public String email;
    public UserRole role;
}
