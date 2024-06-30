package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.PaymentPlanDAO;
import lk.ijse.sithumya.entity.PaymentPlan;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentPlanDAOImpl implements PaymentPlanDAO {

    @Override
    public int getTotalPlanCount() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT COUNT(*) FROM Payment_Plan");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    @Override
    public ArrayList<PaymentPlan> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentPlan> allPlans = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Payment_Plan");
        while (resultSet.next()) {
            PaymentPlan paymentPlan = new PaymentPlan(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
            allPlans.add(paymentPlan);
        }
        return allPlans;
    }

    @Override
    public boolean save(PaymentPlan entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PaymentPlan search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PaymentPlan entity) throws SQLException, ClassNotFoundException {
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
}
