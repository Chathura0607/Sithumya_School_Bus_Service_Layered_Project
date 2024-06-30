package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.GuardianDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GuardianBO extends SuperBO {
    List<String> getAllGuardianEmails() throws SQLException;

    ArrayList<GuardianDTO> getAllGuardians() throws SQLException, ClassNotFoundException;
}
