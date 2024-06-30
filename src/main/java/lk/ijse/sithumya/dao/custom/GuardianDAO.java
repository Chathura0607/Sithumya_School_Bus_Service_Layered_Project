package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.Guardian;

import java.sql.SQLException;
import java.util.List;

public interface GuardianDAO extends CurdDAO<Guardian> {
    List<String> getAllGuardianEmails() throws SQLException;
}
