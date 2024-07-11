package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.GuardianshipDAO;
import lk.ijse.sithumya.entity.Guardianship;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuardianshipDAOImpl implements GuardianshipDAO {
    @Override
    public ArrayList<Guardianship> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Guardianship entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("INSERT INTO Guardianship (Student_ID, Guardian_ID, Emergency_Contact) VALUES (?, ?, ?)", entity.getStudentId(), entity.getGuardianId(), entity.getEmergencyContact());
    }

    @Override
    public Guardianship search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Guardianship entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("UPDATE Guardianship SET Emergency_Contact = ? WHERE Student_ID = ? AND Guardian_ID = ?", entity.getEmergencyContact(), entity.getStudentId(), entity.getGuardianId());
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public String getEmergencyContact(String selectedStudentId) throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT Emergency_Contact FROM Guardianship WHERE Student_ID = ?", selectedStudentId);
        if (resultSet.next()) {
            return resultSet.getString("Emergency_Contact");
        }
        return null;
    }
}
