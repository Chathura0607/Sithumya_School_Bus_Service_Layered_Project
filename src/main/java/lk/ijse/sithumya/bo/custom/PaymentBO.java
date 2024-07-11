package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    double getTotalPaymentAmount() throws SQLException;

    ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;

    boolean savePayment(PaymentDTO payment) throws SQLException, ClassNotFoundException;

    double getRemainingFeeAmount(String studentId) throws SQLException;
}
