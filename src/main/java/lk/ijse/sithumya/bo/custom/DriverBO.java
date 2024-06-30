package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.DriverDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DriverBO extends SuperBO {
    List<String> getAllDriverIds() throws SQLException, ClassNotFoundException;

    ArrayList<DriverDTO> getAllDrivers() throws SQLException, ClassNotFoundException;
}
