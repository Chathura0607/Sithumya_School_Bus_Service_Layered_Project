package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.GuardianDAO;
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
        return null;
    }

    @Override
    public boolean save(Object entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Object entity) throws SQLException {
        return false;
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
    public Object search(String id) throws SQLException {
        return null;
    }
}
