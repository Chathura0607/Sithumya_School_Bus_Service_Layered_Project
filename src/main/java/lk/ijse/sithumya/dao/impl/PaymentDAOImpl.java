package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.PaymentDAO;
import lk.ijse.sithumya.entity.Payment;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public double getTotalPaymentAmount() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT SUM(Amount) AS TotalAmount FROM Payment;");
        return resultSet.next() ? resultSet.getDouble("TotalAmount") : 0.00;
    }
}
