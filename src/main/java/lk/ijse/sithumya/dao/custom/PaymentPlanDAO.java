package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.PaymentPlan;

import java.sql.SQLException;

public interface PaymentPlanDAO extends CurdDAO<PaymentPlan> {
    int getTotalPlanCount() throws SQLException;
}
