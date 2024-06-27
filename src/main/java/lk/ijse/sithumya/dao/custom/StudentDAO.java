package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.Student;

import java.sql.SQLException;

public interface StudentDAO extends CurdDAO <Student> {
    int getTotalStudentsCount() throws SQLException;
}
