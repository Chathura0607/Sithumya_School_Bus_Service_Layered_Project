package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface DriverBO extends SuperBO {
    List<String> getAllDriverIds() throws SQLException, ClassNotFoundException;
}
