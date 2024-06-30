package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.PaymentBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.PaymentDAO;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public double getTotalPaymentAmount() throws SQLException {
        return paymentDAO.getTotalPaymentAmount();
    }
}
