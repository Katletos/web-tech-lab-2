package by.bsuir.domain.entities;

import by.bsuir.domain.enums.UserRole;

public class User {
    private Long id;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String email;
    private String passwordHash;
    private UserRole role;
}
