package by.bsuir.domain.entities;

import java.time.ZonedDateTime;

public class Prescription {
    private Long id;
    private Long doctorId;
    private Long userId;
    private Long drugId;
    private ZonedDateTime expirationDate;
    private ZonedDateTime receiptDate;
}
