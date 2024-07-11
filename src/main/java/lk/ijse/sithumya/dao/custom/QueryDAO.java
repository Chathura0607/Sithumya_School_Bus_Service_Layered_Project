package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.SuperDAO;
import lk.ijse.sithumya.entity.GuardianshipDetails;
import lk.ijse.sithumya.entity.TodayPayments;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<GuardianshipDetails> getAllGuardianshipDetails() throws SQLException;

    ArrayList<TodayPayments> loadTodayPayments() throws SQLException;
}
