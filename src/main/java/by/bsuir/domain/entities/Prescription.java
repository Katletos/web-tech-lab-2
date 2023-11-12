package by.bsuir.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
public class Prescription {
    private Long id;
    private Long doctorId;
    private Long userId;
    private Long drugId;
    private Instant expirationDate;
    private Instant receiptDate;
}
