package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.GuardianDAO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.Guardian;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuardianDAOImpl implements GuardianDAO {
    @Override
    public List<String> getAllGuardianEmails() throws SQLException {
        List<String> emails = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT Email FROM Guardian");
        while (resultSet.next()) {
            emails.add(resultSet.getString("Email"));
        }
        return emails;
    }

    @Override
    public ArrayList getAll() throws SQLException {
        ArrayList<Guardian> allGuardians = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Guardian");
        while (resultSet.next()) {
            Guardian guardian = new Guardian(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            allGuardians.add(guardian);
        }
        return allGuardians;
    }

    @Override
    public boolean save(Guardian entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Guardian search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return List.of();
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Guardian entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}
