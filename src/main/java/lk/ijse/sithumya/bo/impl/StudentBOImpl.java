package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.StudentBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.FeeDAO;
import lk.ijse.sithumya.dao.custom.StudentDAO;
import lk.ijse.sithumya.dto.FeeDTO;
import lk.ijse.sithumya.dto.StudentDTO;
import lk.ijse.sithumya.entity.Fee;
import lk.ijse.sithumya.entity.Student;
import lk.ijse.sithumya.util.TransactionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.STUDENT);
    FeeDAO feeDAO = (FeeDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.FEE);

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        ArrayList<Student> all = studentDAO.getAll();

        for (Student student : all) {
            allStudents.add(new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getDateOfBirth(),
                    student.getSchool(),
                    student.getDistance(),
                    student.getBusId(),
                    student.getPlanId()
            ));
        }
        return allStudents;
    }

    @Override
    public int getTotalStudentsCount() throws SQLException, ClassNotFoundException {
        return studentDAO.getTotalStudentsCount();
    }

    @Override
    public List<String> getAllStudentsIds() throws SQLException, ClassNotFoundException {
        return studentDAO.getAllIds();
    }

    @Override
    public String getCurrentPaymentPlan(String selectedStudentId) throws SQLException {
        return studentDAO.getCurrentPlan(selectedStudentId);
    }

    @Override
    public StudentDTO searchStudent(String studentId) throws SQLException, ClassNotFoundException {
        Student student = studentDAO.search(studentId);
        if (student != null) {
            return new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getDateOfBirth(),
                    student.getSchool(),
                    student.getDistance(),
                    student.getBusId(),
                    student.getPlanId()
            );
        }
        return null;
    }

    @Override
    public String generateNextStudentId() throws ClassNotFoundException, SQLException {
        return studentDAO.generateNewId();
    }

    @Override
    public boolean saveStudentAndFee(StudentDTO studentDTO, FeeDTO feeDTO) throws SQLException, ClassNotFoundException {
        try {
            TransactionUtil.startTransaction();

            boolean isStudentSaved = studentDAO.save(new Student(
                    studentDTO.getStudentId(),
                    studentDTO.getName(),
                    studentDTO.getDateOfBirth(),
                    studentDTO.getSchool(),
                    studentDTO.getDistance(),
                    studentDTO.getBusId(),
                    studentDTO.getPlanId())
            );

            boolean isFeeSaved = feeDAO.save(new Fee(
                    feeDTO.getFeeId(),
                    feeDTO.getStudentId(),
                    feeDTO.getPlanId(),
                    feeDTO.getMonthlyFee(),
                    feeDTO.getDiscount(),
                    feeDTO.getTotalAmount(),
                    feeDTO.getDueDate())
            );

            if (isStudentSaved && isFeeSaved) {
                TransactionUtil.endTransaction();
                return true;
            } else {
                TransactionUtil.rollBack();
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            TransactionUtil.rollBack();
            throw e;
        } finally {
            TransactionUtil.endTransaction();
        }
    }

    @Override
    public boolean updateStudentAndFee(StudentDTO studentDTO, FeeDTO feeDTO) throws SQLException, ClassNotFoundException {
        try {
            TransactionUtil.startTransaction();

            Student student = new Student(
                    studentDTO.getStudentId(),
                    studentDTO.getName(),
                    studentDTO.getDateOfBirth(),
                    studentDTO.getSchool(),
                    studentDTO.getDistance(),
                    studentDTO.getBusId(),
                    studentDTO.getPlanId()
            );

            Fee fee = new Fee(
                    feeDTO.getFeeId(),
                    feeDTO.getStudentId(),
                    feeDTO.getPlanId(),
                    feeDTO.getMonthlyFee(),
                    feeDTO.getDiscount(),
                    feeDTO.getTotalAmount(),
                    feeDTO.getDueDate()
            );

            boolean studentUpdated = studentDAO.update(student);
            boolean feeUpdated = feeDAO.update(fee);

            if (studentUpdated && feeUpdated) {
                TransactionUtil.endTransaction();
                return true;
            } else {
                TransactionUtil.rollBack();
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            TransactionUtil.rollBack();
            System.err.println("Exception occurred: " + e.getMessage());
            throw e;
        } finally {
            TransactionUtil.endTransaction();
        }
    }

    @Override
    public boolean deleteStudentAndFee(String studentId) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(studentId);
    }
}
