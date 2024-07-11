package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.GuardianDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GuardianBO extends SuperBO {
    List<String> getAllGuardianEmails() throws SQLException;

    ArrayList<GuardianDTO> getAllGuardians() throws SQLException, ClassNotFoundException;

    GuardianDTO searchGuardian(String guardianId) throws SQLException, ClassNotFoundException;

    boolean deleteGuardian(String guardianId) throws SQLException, ClassNotFoundException;

    boolean saveGuardianWithGuardianship(GuardianDTO guardian, String studentId, String emergencyContact) throws SQLException, ClassNotFoundException;

    boolean updateGuardianWithGuardianship(GuardianDTO guardian, String studentId, String emergencyContact) throws SQLException, ClassNotFoundException;
}
