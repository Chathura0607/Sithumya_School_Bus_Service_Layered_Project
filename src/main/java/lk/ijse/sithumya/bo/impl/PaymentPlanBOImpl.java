package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.PaymentPlanBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.PaymentPlanDAO;
import lk.ijse.sithumya.dto.GuardianDTO;
import lk.ijse.sithumya.dto.PaymentPlanDTO;
import lk.ijse.sithumya.dto.StockDTO;
import lk.ijse.sithumya.entity.Guardian;
import lk.ijse.sithumya.entity.PaymentPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentPlanBOImpl implements PaymentPlanBO {
    private PaymentPlanDAO paymentPlanDAO = (PaymentPlanDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.PLAN);

    @Override
    public int getTotalPlanCount() throws SQLException {
        return paymentPlanDAO.getTotalPlanCount();
    }

    @Override
    public ArrayList<PaymentPlanDTO> getAllPaymentPlans() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentPlanDTO> allPlans = new ArrayList<>();
        ArrayList<PaymentPlan> all = paymentPlanDAO.getAll();

        for (PaymentPlan paymentPlan : all) {
            allPlans.add(new PaymentPlanDTO(
                    paymentPlan.getPlanId(),
                    paymentPlan.getPlanName(),
                    paymentPlan.getNumberOfInstallments()
            ));
        }
        return allPlans;
    }
}
