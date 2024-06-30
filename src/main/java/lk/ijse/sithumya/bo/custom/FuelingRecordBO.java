package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.FuelingRecordDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FuelingRecordBO extends SuperBO {
    ArrayList<FuelingRecordDTO> getAllFuelingRecords() throws SQLException, ClassNotFoundException;
}
