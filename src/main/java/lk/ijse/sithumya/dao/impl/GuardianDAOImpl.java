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
}
