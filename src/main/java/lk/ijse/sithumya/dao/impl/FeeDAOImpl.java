package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.FeeDAO;
import lk.ijse.sithumya.entity.Fee;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FeeDAOImpl implements FeeDAO {
    @Override
    public ArrayList<Fee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Fee> allFees = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Fee");

        while (resultSet.next()) {
            Fee fee = new Fee(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getDate(7)
            );
            allFees.add(fee);
        }
        return allFees;
    }

    @Override
    public boolean save(Fee entity) throws SQLException, ClassNotFoundException {
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.lengthOfMonth());

        return SqlUtil.sql("INSERT INTO Fee (Student_ID, Payment_Plan_ID, Monthly_Fee, Discount, Total_Amount, Due_Date) VALUES (?, ?, ?, ?, ?, ?)",
                entity.getStudentId(),
                entity.getPlanId(),
                entity.getMonthlyFee(),
                entity.getDiscount(),
                entity.getTotalAmount(),
                Date.valueOf(dueDate));
    }

    @Override
    public Fee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Fee entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("UPDATE Fee SET Payment_Plan_ID = ? WHERE Student_ID = ?", entity.getPlanId(), entity.getStudentId());
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
    public double getRemainingFeeAmount(String studentId) throws SQLException {
        String sql = "SELECT Total_Amount FROM Fee WHERE Student_ID = ? ORDER BY Fee_ID DESC LIMIT 1";
        ResultSet resultSet = SqlUtil.sql(sql, studentId);
        if (resultSet.next()) {
            return resultSet.getDouble("Total_Amount");
        }
        return 0;
    }

    @Override
    public boolean updateFeeAmount(String studentId, double newFeeAmount) throws SQLException {
        return SqlUtil.sql("UPDATE Fee SET Total_Amount = ? WHERE Student_ID = ? ORDER BY Fee_ID DESC LIMIT 1", newFeeAmount, studentId);
    }

    @Override
    public boolean updateMonthlyFee(Fee entity) throws SQLException {
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.lengthOfMonth());

        return SqlUtil.sql("UPDATE Fee SET Payment_Plan_ID = ?, Monthly_Fee = ?, Discount = ?, Total_Amount = ?, Due_Date = ? WHERE Student_ID = ?",
                entity.getPlanId(),
                entity.getMonthlyFee(),
                entity.getDiscount(),
                entity.getTotalAmount(),
                Date.valueOf(dueDate),
                entity.getStudentId());
    }
}
