package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.SalaryBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.SalaryDAO;
import lk.ijse.sithumya.dto.DriverDTO;
import lk.ijse.sithumya.entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.SALARY);

    @Override
    public ArrayList<DriverDTO> getAllDriversWithSalary() throws SQLException, ClassNotFoundException {
        ArrayList<DriverDTO> allDrivers = new ArrayList<>();
        ArrayList<Driver> all = salaryDAO.getAll();

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
