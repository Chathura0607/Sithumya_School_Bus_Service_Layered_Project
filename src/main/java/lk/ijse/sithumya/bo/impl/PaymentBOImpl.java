package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.PaymentBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.FeeDAO;
import lk.ijse.sithumya.dao.custom.PaymentDAO;
import lk.ijse.sithumya.dto.PaymentDTO;
import lk.ijse.sithumya.entity.Payment;
import lk.ijse.sithumya.util.TransactionUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.PAYMENT);
    FeeDAO feeDAO = (FeeDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.FEE);

    @Override
    public double getTotalPaymentAmount() throws SQLException {
        return paymentDAO.getTotalPaymentAmount();
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> allPayments = new ArrayList<>();
        ArrayList<Payment> all = paymentDAO.getAll();

        for (Payment payment : all) {
            allPayments.add(new PaymentDTO(
                    payment.getPaymentId(),
                    payment.getStudentId(),
                    payment.getPaymentPlanId(),
                    payment.getAmount(),
                    payment.getPaymentDate(),
                    payment.getIsCompleted()
            ));
        }
        return allPayments;
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        Payment payment = new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getStudentId(),
                paymentDTO.getPaymentPlanId(),
                paymentDTO.getAmount(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getIsCompleted()
        );

        try {
            TransactionUtil.startTransaction();
            boolean isSaved = paymentDAO.save(payment);
            if (!isSaved) {
                TransactionUtil.rollBack();
                return false;
            }

            boolean isFeeUpdated = updateFeeAfterPayment(payment);
            if (!isFeeUpdated) {
                TransactionUtil.rollBack();
                return false;
            }

            TransactionUtil.endTransaction();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            TransactionUtil.rollBack();
            throw e;
        } finally {
            TransactionUtil.endTransaction();
        }
    }

    @Override
    public double getRemainingFeeAmount(String studentId) throws SQLException {
        return feeDAO.getRemainingFeeAmount(studentId);
    }

    private boolean updateFeeAfterPayment(Payment payment) throws SQLException {
        double remainingFeeAmount = feeDAO.getRemainingFeeAmount(payment.getStudentId());
        double newFeeAmount = remainingFeeAmount - payment.getAmount();

        return feeDAO.updateFeeAmount(payment.getStudentId(), newFeeAmount);
    }
}
