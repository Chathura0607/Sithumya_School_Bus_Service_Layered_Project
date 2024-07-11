package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.PenaltyDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PenaltyBO extends SuperBO {
    ArrayList<PenaltyDetailsDTO> getAllPenalties() throws SQLException;
}
