package by.bsuir.dao.implementation;

import by.bsuir.application.dtos.UpdatePrescriptionDto;
import by.bsuir.dao.DaoException;
import by.bsuir.dao.connectionpool.ConnectionPool;
import by.bsuir.dao.connectionpool.ConnectionPoolException;
import by.bsuir.domain.entities.Prescription;

import java.sql.*;
import java.util.ArrayList;
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
    public List<Prescription> getPrescriptionsByUserId(Long userId, Boolean isDoctor) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Prescription> prescriptions = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(
                    "UPDATE prescriptions " +
                            "SET receipt_date = ?, expiration_date = ? " +
                            "WHERE id = ?");

            resultSet = statement.executeQuery();
            var tmpPrescription = new Prescription();

            while (resultSet.next()){
                tmpPrescription.setId(resultSet.getLong(1));
                tmpPrescription.setUserId(resultSet.getLong(2));
                tmpPrescription.setDoctorId(resultSet.getLong(3));
                tmpPrescription.setDrugId(resultSet.getLong(4));
                tmpPrescription.setReceiptDate(resultSet.getTimestamp(5).toInstant());
                tmpPrescription.setExpirationDate(resultSet.getTimestamp(6).toInstant());
                prescriptions.add(tmpPrescription);
            }

            return prescriptions;
        } catch (ConnectionPoolException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prescription renewPrescription(Prescription prescription) throws DaoException {
        PreparedStatement statement = null;
        try {
            var connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(
                    "UPDATE prescriptions " +
                    "SET receipt_date = ?, expiration_date = ? " +
                    "WHERE id = ?");

            statement.setTimestamp(1, Timestamp.from(prescription.getReceiptDate()));
            statement.setTimestamp(2, Timestamp.from(prescription.getExpirationDate()));
            statement.setLong(3, prescription.getId());

            var rows = statement.executeUpdate();
            if (rows != 1){
                throw new DaoException("Can't renew prescription");
            }

            return getPrescriptionById(prescription.getId());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Can't renew prescription", e);
        }
    }
}
