package by.bsuir.application.dtos;

import by.bsuir.domain.enums.UserRole;

public class UpdateUserDto {
    public String firstName;
    public String lastName;
    public String patronymic;
    public String email;
    public String passwordHash;
    public UserRole role;
}
