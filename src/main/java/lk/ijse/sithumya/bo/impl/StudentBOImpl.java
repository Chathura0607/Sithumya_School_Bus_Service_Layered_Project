package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.StudentBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.StudentDAO;
import lk.ijse.sithumya.dto.StudentDTO;
import lk.ijse.sithumya.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.STUDENT);

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
}
