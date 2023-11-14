package by.bsuir.application.interfaces;

import by.bsuir.application.BuisenessRuleException;
import by.bsuir.application.dtos.UpdatePrescriptionDto;
import by.bsuir.domain.entities.Prescription;

public interface PrescriptionService {
    Boolean addPrescription(UpdatePrescriptionDto dto) throws BuisenessRuleException;

    Prescription renewPrescription(Long prescriptionId, Long doctorId) throws BuisenessRuleException;
}
