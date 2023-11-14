package by.bsuir.dao.implementation;

import by.bsuir.dao.DaoException;
import by.bsuir.dao.connectionpool.ConnectionPool;
import by.bsuir.dao.connectionpool.ConnectionPoolException;
import by.bsuir.dao.interfaces.DrugDao;
import by.bsuir.domain.entities.Drug;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugDAO implements DrugDao {
    @Override
    public Drug addDrug() {
        return null;
    }

    @Override
    public Drug getDrugById(Long drugId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Drug drug = null;
        try {
            var connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement("SELECT id, name, has_prescription FROM drugs WHERE id = ?");
            statement.setLong(1, drugId);

            resultSet = statement.executeQuery();

            if(resultSet.next()){
                drug = new Drug();
                drug.setId(resultSet.getLong(1));
                drug.setName(resultSet.getString(2));
                drug.setHasPrescription(resultSet.getBoolean(3));
            }

            return drug;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Can't get prescription", e);
        }

    }
}
