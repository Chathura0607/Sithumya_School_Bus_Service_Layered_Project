package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.UserDAO;
import lk.ijse.sithumya.entity.User;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findUserByName(String userName) throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM User WHERE Name = ?", userName);

        if (resultSet.next()) {
            return new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }

    @Override
    public void resetPassword(User entity) throws SQLException {
        SqlUtil.sql("UPDATE User SET Password = ? WHERE Name = ? AND User_ID = ?", entity.getPassword(), entity.getUserName(), entity.getUserId());
    }
}
