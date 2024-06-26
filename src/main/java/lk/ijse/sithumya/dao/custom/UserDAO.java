package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDAO extends CurdDAO {
    User findUserByName(String userName) throws SQLException;

    void resetPassword(User entity) throws SQLException;
}
