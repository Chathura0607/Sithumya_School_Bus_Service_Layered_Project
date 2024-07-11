package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.GuardianshipDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuardianshipBO extends SuperBO {
    ArrayList<GuardianshipDTO> getAllGuardians() throws SQLException, ClassNotFoundException;

    String getEmergencyContact(String selectedStudentId) throws SQLException, ClassNotFoundException;
}
