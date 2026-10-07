package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.FeeDTO;
import lk.ijse.sithumya.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException;

    int getTotalStudentsCount() throws SQLException, ClassNotFoundException;

    List<String> getAllStudentsIds() throws SQLException, ClassNotFoundException;

    String getCurrentPaymentPlan(String selectedStudentId) throws SQLException, ClassNotFoundException;

    StudentDTO searchStudent(String studentId) throws SQLException, ClassNotFoundException;

    String generateNextStudentId() throws ClassNotFoundException, SQLException;

    boolean saveStudentAndFee(StudentDTO studentDTO, FeeDTO feeDTO) throws SQLException, ClassNotFoundException;

    boolean updateStudentAndFee(StudentDTO studentDTO, FeeDTO feeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteStudentAndFee(String studentId) throws SQLException, ClassNotFoundException;
}
