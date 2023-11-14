package by.bsuir.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drug {
    private Long id;
    private String name;
    private Boolean hasPrescription;
}
