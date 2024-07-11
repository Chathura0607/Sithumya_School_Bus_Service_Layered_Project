package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.Fee;

import java.sql.SQLException;

public interface FeeDAO extends CurdDAO<Fee> {
    double getRemainingFeeAmount(String studentId) throws SQLException;

    boolean updateFeeAmount(String studentId, double newFeeAmount) throws SQLException;

    boolean updateMonthlyFee(Fee fee) throws SQLException;
}
