package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.DriverDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    ArrayList<DriverDTO> getAllDriversWithSalary() throws SQLException, ClassNotFoundException;

    boolean updateSalary(DriverDTO driverDTO) throws SQLException, ClassNotFoundException;

    DriverDTO salarySearchByDriverId(String driverId) throws SQLException, ClassNotFoundException;
}
