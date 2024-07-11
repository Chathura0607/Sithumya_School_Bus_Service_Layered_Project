package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.QueryDAO;
import lk.ijse.sithumya.entity.GuardianshipDetails;
import lk.ijse.sithumya.entity.TodayPayments;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<GuardianshipDetails> getAllGuardianshipDetails() throws SQLException {
        ArrayList<GuardianshipDetails> allGuardians = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT s.Student_ID, s.Name AS Student_Name, gs.Guardian_ID, g.Name AS Guardian_Name, gs.Emergency_Contact, g.Relation " +
                "FROM Student s " +
                "JOIN Guardianship gs ON s.Student_ID = gs.Student_ID " +
                "JOIN Guardian g ON gs.Guardian_ID = g.Guardian_ID");

        while (resultSet.next()) {
            allGuardians.add(new GuardianshipDetails (
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return allGuardians;
    }

    @Override
    public ArrayList<TodayPayments> loadTodayPayments() throws SQLException {
        ArrayList<TodayPayments> allTodayPayments = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT DISTINCT p.Payment_ID, s.Name AS Student_Name, pp.Name AS Payment_Plan_Name, p.Amount AS Payment_Amount \n" +
                "FROM Payment p \n" +
                "JOIN Fee f ON p.Payment_Plan_ID = f.Payment_Plan_ID \n" +
                "JOIN Student s ON f.Student_ID = s.Student_ID \n" +
                "JOIN Payment_Plan pp ON f.Payment_Plan_ID = pp.Payment_Plan_ID \n" +
                "WHERE DATE(p.Payment_Date) = CURDATE();");

        while (resultSet.next()) {
            allTodayPayments.add(new TodayPayments(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)

            ));
        }
        return allTodayPayments;
    }
}