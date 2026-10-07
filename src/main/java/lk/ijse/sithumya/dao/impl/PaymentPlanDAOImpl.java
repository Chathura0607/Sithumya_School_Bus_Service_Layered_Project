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
        return SqlUtil.sql("INSERT INTO Payment_Plan (Payment_Plan_ID, Name, Number_Of_Installments) VALUES (?,?,?)", entity.getPlanId(), entity.getPlanName(), entity.getNumberOfInstallments());
    }

    @Override
    public PaymentPlan search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Payment_Plan WHERE Payment_Plan_ID = ?", id);
        if (resultSet.next()) {
            return new PaymentPlan(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("DELETE FROM Payment_Plan WHERE Payment_Plan_ID = ?", id);
    }

    @Override
    public boolean update(PaymentPlan entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("UPDATE Payment_Plan SET Name = ?, Number_Of_Installments = ? WHERE Payment_Plan_ID = ?", entity.getPlanName(), entity.getNumberOfInstallments(), entity.getPlanId());
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT Payment_Plan_ID FROM Payment_Plan ORDER BY Payment_Plan_ID DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("Payment_Plan_ID");
            String numericPart = id.replaceAll("\\D", "");
            int newCustomerId = Integer.parseInt(numericPart) + 1;
            return String.format("P%03d", newCustomerId);
        } else {
            return "P001";
        }
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        List<String> planIds = new ArrayList<>();

        try (ResultSet resultSet = SqlUtil.sql("SELECT Payment_Plan_ID FROM Payment_Plan")) {
            while (resultSet.next()) {
                String studentId = resultSet.getString("Payment_Plan_ID");
                planIds.add(studentId);
            }
        }
        return planIds;
    }
}
