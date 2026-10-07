package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.DriverDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DriverBO extends SuperBO {
    List<String> getAllDriverIds() throws SQLException, ClassNotFoundException;

    ArrayList<DriverDTO> getAllDrivers() throws SQLException, ClassNotFoundException;

    boolean saveDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException;

    DriverDTO searchDriver(String driverId) throws SQLException, ClassNotFoundException;

    boolean updateDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException;

    boolean deleteDriver(String driverId) throws SQLException;
}
