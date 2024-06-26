package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface GuardianBO extends SuperBO {
    List<String> getAllGuardianEmails() throws SQLException;
}
