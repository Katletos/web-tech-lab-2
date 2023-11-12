package by.bsuir.dao.interfaces;

import by.bsuir.application.dtos.UpdatePrescriptionDto;
import by.bsuir.dao.DaoException;
import by.bsuir.domain.entities.Prescription;

import java.util.List;


public interface PrescriptionDAO {
    Boolean addPrescription(UpdatePrescriptionDto dto) throws DaoException;

    Prescription getPrescriptionById(Long prescriptionId);

    List<Prescription> getPrescriptionsByUserId(Long userId, Boolean isDoctor);

    Prescription renewPrescription(Prescription prescription) throws DaoException;
}
