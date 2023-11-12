package by.bsuir.domain.entities;

import by.bsuir.domain.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String passwordHash;
    private UserRole role;
}
