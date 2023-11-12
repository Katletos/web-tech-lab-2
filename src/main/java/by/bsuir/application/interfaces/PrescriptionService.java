package by.bsuir.application.interfaces;

import by.bsuir.application.BuisenessRuleException;
import by.bsuir.application.dtos.UpdatePrescriptionDto;

public interface PrescriptionService {
    Boolean addPrescription(UpdatePrescriptionDto dto) throws BuisenessRuleException;

    void renewPrescription(Long prescriptionId) throws BuisenessRuleException;
}
