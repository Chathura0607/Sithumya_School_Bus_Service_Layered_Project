package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.DriverBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.DriverDAO;

import java.sql.SQLException;
import java.util.List;

public class DriverBOImpl implements DriverBO {
    private DriverDAO driverDAO = (DriverDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.DRIVER);

    @Override
    public List<String> getAllDriverIds() throws SQLException, ClassNotFoundException {
        return driverDAO.getAllIds();
    }
}
