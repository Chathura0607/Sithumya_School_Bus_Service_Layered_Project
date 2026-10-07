package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.PaymentPlanDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentPlanBO extends SuperBO {
    int getTotalPlanCount() throws SQLException, ClassNotFoundException;

    ArrayList<PaymentPlanDTO> getAllPaymentPlans() throws SQLException, ClassNotFoundException;

    boolean savePlan(PaymentPlanDTO paymentPlanDTO) throws SQLException, ClassNotFoundException;

    boolean updatePaymentPlan(PaymentPlanDTO paymentPlanDTO) throws SQLException, ClassNotFoundException;

    PaymentPlanDTO searchPaymentPlan(String planId) throws SQLException, ClassNotFoundException;

    boolean deletePlan(String paymentPlanId) throws SQLException, ClassNotFoundException;

    List<String> getAllPlanIds() throws SQLException, ClassNotFoundException;

    String generateNextPlanId() throws SQLException, ClassNotFoundException;
}
