package by.bsuir.dao.interfaces;

import by.bsuir.domain.entities.Drug;

public interface DrugDao {
    Drug addDrug();

    Drug getDrugById(Long drugId);
}
