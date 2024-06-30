package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    double getTotalPaymentAmount() throws SQLException;
}
