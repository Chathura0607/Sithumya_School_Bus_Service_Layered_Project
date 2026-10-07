package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.Guardianship;

import java.sql.SQLException;

public interface GuardianshipDAO extends CurdDAO<Guardianship> {
    String getEmergencyContact(String selectedStudentId) throws SQLException;
}
