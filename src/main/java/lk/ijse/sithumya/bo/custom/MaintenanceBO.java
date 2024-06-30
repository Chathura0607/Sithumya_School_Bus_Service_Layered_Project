package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.MaintenanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MaintenanceBO extends SuperBO {
    List<String> getAllMaintenanceIds() throws SQLException, ClassNotFoundException;

    ArrayList<MaintenanceDTO> getAllMaintenance() throws SQLException, ClassNotFoundException;
}
