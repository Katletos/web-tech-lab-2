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
    private final String SELECT_PRESCRIPTION =
            "SELECT id, doctor_id, user_id, drug_id, expiration_date, receipt_date FROM prescriptions";


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
    public Prescription getPrescriptionById(Long prescriptionId) throws DaoException {
        Prescription prescription = null;
        try {
            var connection = ConnectionPool.getConnection();
            var statement = connection.prepareStatement(SELECT_PRESCRIPTION + " WHERE id = ?");
            statement.setLong(1, prescriptionId);

            var resultSet = statement.executeQuery();


            if (resultSet.next()){
                prescription = new Prescription();
                prescription.setId(resultSet.getLong(1));
                prescription.setUserId(resultSet.getLong(2));
                prescription.setDoctorId(resultSet.getLong(3));
                prescription.setDrugId(resultSet.getLong(4));
                prescription.setReceiptDate(resultSet.getTimestamp(5).toInstant());
                prescription.setExpirationDate(resultSet.getTimestamp(6).toInstant());
            }

            return prescription;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("");
        }

    }

    @Override
    public List<Prescription> getPrescriptionsByUserId(Long userId, Boolean isDoctor) throws DaoException {
        try {
            PreparedStatement statement = null;
            var connection = ConnectionPool.getConnection();

            if (isDoctor){
                statement = connection.prepareStatement(SELECT_PRESCRIPTION + " WHERE doctor_id = ?");
            } else {
                statement = connection.prepareStatement(SELECT_PRESCRIPTION + " WHERE user_id = ?");
            }
            statement.setLong(1, userId);

            var resultSet = statement.executeQuery();
            List<Prescription> prescriptions = new ArrayList<>();
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
            throw new DaoException("Can't renew prescription", e);
        }
    }

    @Override
    public Boolean renewPrescription(Prescription prescription) throws DaoException {
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

            var success = statement.execute();
            if (!success){
                throw new DaoException("Can't renew prescription");
            }

            return true;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Can't renew prescription", e);
        }
    }
}
