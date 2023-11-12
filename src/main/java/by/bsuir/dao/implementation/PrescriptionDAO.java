package by.bsuir.dao.implementation;

import by.bsuir.application.dtos.UpdatePrescriptionDto;
import by.bsuir.dao.DaoException;
import by.bsuir.dao.connectionpool.ConnectionPool;
import by.bsuir.dao.connectionpool.ConnectionPoolException;
import by.bsuir.domain.entities.Prescription;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class PrescriptionDAO implements by.bsuir.dao.interfaces.PrescriptionDAO {
    @Override
    public Boolean addPrescription(UpdatePrescriptionDto dto) throws DaoException {
        PreparedStatement statement = null;
        try {
            var connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement("INSERT INTO prescriptions " +
                    "(id, doctor_id, user_id, drug_id, expiration_date, receipt_date) " +
                    "VALUES (NEXTVAL('prescriptions_id_seq'), ?, ?, ?, ?, ?)");

            statement.setLong(1, dto.doctorId);
            statement.setLong(2, dto.userId);
            statement.setLong(3, dto.drugId);
            statement.setTimestamp(4, dto.expirationDate);
            statement.setTimestamp(5, dto.receiptDate);

            return statement.execute();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Can't add prescription", e);
        }
    }

    @Override
    public Prescription getPrescriptionById(Long prescriptionId) {
        return null;
    }

    @Override
    public List<Prescription> getPrescriptionsByUserId(Long userId, Boolean isDoctor) {
        return null;
    }

    @Override
    public Prescription renewPrescription(Prescription prescription) throws DaoException {
        PreparedStatement statement = null;
        try {
            var connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement("UPDATE prescriptions SET receipt_date = ?, expiration_date = ?");

            statement.setTimestamp(1, Timestamp.from(prescription.getReceiptDate()));
            statement.setTimestamp(2, Timestamp.from(prescription.getExpirationDate()));

//            return statement.execute();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Can't add prescription", e);
        }
        return null;
    }
}
