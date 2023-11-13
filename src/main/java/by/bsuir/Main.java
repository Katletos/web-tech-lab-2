package by.bsuir;

import by.bsuir.application.dtos.UpdatePrescriptionDto;
import by.bsuir.application.impementation.PrescriptionService;
import by.bsuir.application.impementation.UserService;
import by.bsuir.dao.connectionpool.ConnectionPool;
import by.bsuir.dao.connectionpool.ConnectionPoolException;
import by.bsuir.domain.entities.Prescription;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class Main {
    @SneakyThrows
    public static void main(String[] args) throws SQLException {
        try {
            var connection = ConnectionPool.getConnection();
            System.out.println(connection.isClosed());
            UpdatePrescriptionDto dto = new UpdatePrescriptionDto();
            dto.drugId = 1L;
            dto.userId = 2L;
            dto.doctorId = 1L;
            dto.expirationDate = Timestamp.from(Instant.now());
            dto.receiptDate = Timestamp.from(Instant.now());
            var ps = new PrescriptionService().addPrescription(dto);

            connection.close();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }
}