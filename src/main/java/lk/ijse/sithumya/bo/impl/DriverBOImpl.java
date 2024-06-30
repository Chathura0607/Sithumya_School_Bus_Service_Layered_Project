package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.DriverBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.DriverDAO;
import lk.ijse.sithumya.dto.DriverDTO;
import lk.ijse.sithumya.dto.GuardianDTO;
import lk.ijse.sithumya.entity.Driver;
import lk.ijse.sithumya.entity.Guardian;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverBOImpl implements DriverBO {
    private DriverDAO driverDAO = (DriverDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.DRIVER);

    @Override
    public List<String> getAllDriverIds() throws SQLException, ClassNotFoundException {
        return driverDAO.getAllIds();
    }

    @Override
    public ArrayList<DriverDTO> getAllDrivers() throws SQLException, ClassNotFoundException {
        ArrayList<DriverDTO> allDrivers = new ArrayList<>();
        ArrayList<Driver> all = driverDAO.getAll();

        for (Driver driver : all) {
            allDrivers.add(new DriverDTO(
                    driver.getDriverId(),
                    driver.getName(),
                    driver.getAddress(),
                    driver.getEmail(),
                    driver.getContactNumber(),
                    driver.getSalary(),
                    driver.getPayment(),
                    driver.getPaymentDue()
            ));
        }
        return allDrivers;
    }
}
