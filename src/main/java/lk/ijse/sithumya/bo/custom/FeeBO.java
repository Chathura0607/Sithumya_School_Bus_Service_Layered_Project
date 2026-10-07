package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.FeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeeBO extends SuperBO {
    ArrayList<FeeDTO> getAllFees() throws SQLException, ClassNotFoundException;

    boolean setPlan(FeeDTO feeDTO) throws SQLException, ClassNotFoundException;
}
