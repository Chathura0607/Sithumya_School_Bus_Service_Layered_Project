package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;

import java.sql.SQLException;
import java.util.List;

public interface GuardianDAO extends CurdDAO {
    List<String> getAllGuardianEmails() throws SQLException;
}
