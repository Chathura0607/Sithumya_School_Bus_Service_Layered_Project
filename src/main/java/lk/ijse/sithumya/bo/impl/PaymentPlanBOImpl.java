package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.PaymentPlanBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.PaymentPlanDAO;
import lk.ijse.sithumya.dto.PaymentPlanDTO;
import lk.ijse.sithumya.entity.PaymentPlan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean savePlan(PaymentPlanDTO paymentPlanDTO) throws SQLException, ClassNotFoundException {
        return paymentPlanDAO.save(new PaymentPlan(paymentPlanDTO.getPlanId(), paymentPlanDTO.getPlanName(), paymentPlanDTO.getNumberOfInstallments()));
    }

    @Override
    public boolean updatePaymentPlan(PaymentPlanDTO paymentPlanDTO) throws SQLException, ClassNotFoundException {
        return paymentPlanDAO.update(new PaymentPlan(paymentPlanDTO.getPlanId(), paymentPlanDTO.getPlanName(), paymentPlanDTO.getNumberOfInstallments()));
    }

    @Override
    public PaymentPlanDTO searchPaymentPlan(String planId) throws SQLException, ClassNotFoundException {
        PaymentPlan paymentPlan = paymentPlanDAO.search(planId);

        if (paymentPlan != null) {
            return new PaymentPlanDTO(
                    paymentPlan.getPlanId(),
                    paymentPlan.getPlanName(),
                    paymentPlan.getNumberOfInstallments()
            );
        }
        return null;
    }

    @Override
    public boolean deletePlan(String paymentPlanId) throws SQLException, ClassNotFoundException {
        return paymentPlanDAO.delete(paymentPlanId);
    }

    @Override
    public List<String> getAllPlanIds() throws SQLException, ClassNotFoundException {
        return paymentPlanDAO.getAllIds();
    }

    @Override
    public String generateNextPlanId() throws SQLException, ClassNotFoundException {
        return paymentPlanDAO.generateNewId();
    }
}
