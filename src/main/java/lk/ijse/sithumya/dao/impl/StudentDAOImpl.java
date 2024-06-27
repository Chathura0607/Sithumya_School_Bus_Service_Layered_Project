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
        return false;
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException {
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
    public int getTotalStudentsCount() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT COUNT(*) FROM Student");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }
}
