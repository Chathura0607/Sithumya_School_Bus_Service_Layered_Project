package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.PaymentPlanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentPlanBO extends SuperBO {
    int getTotalPlanCount() throws SQLException, ClassNotFoundException;

    ArrayList<PaymentPlanDTO> getAllPaymentPlans() throws SQLException, ClassNotFoundException;
}
