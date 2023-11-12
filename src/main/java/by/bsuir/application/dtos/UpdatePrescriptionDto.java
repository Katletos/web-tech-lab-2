package by.bsuir.application.dtos;

import java.sql.Timestamp;

public class UpdatePrescriptionDto {
    public Long doctorId;
    public Long userId;
    public Long drugId;
    public Timestamp expirationDate;
    public Timestamp receiptDate;
}
