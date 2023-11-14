package by.bsuir.application.impementation;

import by.bsuir.application.BuisenessRuleException;
import by.bsuir.application.Messages;
import by.bsuir.application.dtos.UpdatePrescriptionDto;
import by.bsuir.dao.DaoException;
import by.bsuir.dao.implementation.DrugDAO;
import by.bsuir.dao.implementation.PrescriptionDAO;
import by.bsuir.dao.implementation.UserDao;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PrescriptionService implements by.bsuir.application.interfaces.PrescriptionService {
    private final UserDao userDao = new UserDao();
    private final PrescriptionDAO prescriptionDAO= new PrescriptionDAO();
    private final DrugDAO drugDAO= new DrugDAO();

    public Boolean addPrescription(UpdatePrescriptionDto dto) throws BuisenessRuleException {
        try {
            if (dto.userId.equals(dto.doctorId))
            {
                throw new BuisenessRuleException("");
                }

            var user = this.userDao.getUserById(dto.userId);
            if (user == null){
                throw new BuisenessRuleException(Messages.userDoesNotExist);
            }

            var doctor = this.userDao.getUserById(dto.doctorId);
            if (doctor == null){
                throw new BuisenessRuleException(Messages.doctorDoesNotExist);
            }

            var drug = this.drugDAO.getDrugById(dto.drugId);
            if (drug == null){
                throw new BuisenessRuleException(Messages.drugNotExist);
            }

            var success = this.prescriptionDAO.addPrescription(dto);
            if (!success)
            {
                throw new BuisenessRuleException(Messages.cantAddPrescription);
            }

            return true;
        } catch (DaoException e) {
            throw new BuisenessRuleException(Messages.cantAddPrescription, e);
        }
    }

    @Override
    public void renewPrescription(Long prescriptionId) throws BuisenessRuleException {
        var prescription = this.prescriptionDAO.getPrescriptionById(prescriptionId);

        if (prescription == null) {
            throw new BuisenessRuleException(Messages.prescriptionDoNotExist);
        }

        var utcTime = Instant.now();
        prescription.setReceiptDate(utcTime);
        prescription.setExpirationDate(utcTime.plus(30, ChronoUnit.DAYS));

        try {
            var sucsess = this.prescriptionDAO.renewPrescription(prescription);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
