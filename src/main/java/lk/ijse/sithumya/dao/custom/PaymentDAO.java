package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CurdDAO<Payment> {
    double getTotalPaymentAmount() throws SQLException;

}
