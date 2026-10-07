package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.StudentDAO;
import lk.ijse.sithumya.entity.Student;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList getAll() throws SQLException {
        ArrayList<Student> allStudents = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Student");
        while (resultSet.next()) {
            Student student = new Student(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            allStudents.add(student);
        }
        return allStudents;
    }

    @Override
    public boolean save(Student entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("INSERT INTO Student (Student_ID, Name, Date_Of_Birth, School_Name, Distance_To_School, Bus_ID, Payment_Plan_ID) VALUES (?,?,?,?,?,?,?)",
                entity.getStudentId(),
                entity.getName(),
                entity.getDateOfBirth(),
                entity.getSchool(),
                entity.getDistance(),
                entity.getBusId(),
                entity.getPlanId());
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT Student_ID, Name, Date_Of_Birth, School_Name, Distance_To_School, Bus_ID, Payment_Plan_ID FROM Student WHERE Student_ID = ?", id);
        if (resultSet.next()) {
            return new Student(
                    resultSet.getString("Student_ID"),
                    resultSet.getString("Name"),
                    resultSet.getDate("Date_Of_Birth"),
                    resultSet.getString("School_Name"),
                    resultSet.getDouble("Distance_To_School"),
                    resultSet.getString("Bus_ID"),
                    resultSet.getString("Payment_Plan_ID")
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("DELETE FROM Student WHERE Student_ID = ?", id);
    }

    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("UPDATE Student SET Name = ?, Date_Of_Birth = ?, School_Name = ?, Distance_To_School = ?, Bus_ID = ?, Payment_Plan_ID = ? WHERE Student_ID = ?",
                entity.getName(),
                entity.getDateOfBirth(),
                entity.getSchool(),
                entity.getDistance(),
                entity.getBusId(),
                entity.getPlanId(),
                entity.getStudentId());
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT Student_ID FROM Student ORDER BY Student_ID DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("Student_ID");
            String numericPart = id.replaceAll("\\D", "");
            int newCustomerId = Integer.parseInt(numericPart) + 1;
            return String.format("S%03d", newCustomerId);
        } else {
            return "S001";
        }
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        List<String> studentIds = new ArrayList<>();

        try (ResultSet resultSet = SqlUtil.sql("SELECT Student_ID FROM Student")) {
            while (resultSet.next()) {
                String studentId = resultSet.getString("Student_ID");
                studentIds.add(studentId);
            }
        }
        return studentIds;
    }

    @Override
    public int getTotalStudentsCount() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT COUNT(*) FROM Student");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    @Override
    public String getCurrentPlan(String selectedStudentId) throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT Payment_Plan_ID FROM Fee WHERE Student_ID = ?", selectedStudentId);
        if (resultSet.next()) {
            return resultSet.getString("Payment_Plan_ID");
        }
        return null;
    }
}
