package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.GuardianshipBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.GuardianshipDAO;
import lk.ijse.sithumya.dao.custom.QueryDAO;
import lk.ijse.sithumya.dto.GuardianshipDTO;
import lk.ijse.sithumya.entity.GuardianshipDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuardianshipBOImpl implements GuardianshipBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.QUERY);
    GuardianshipDAO guardianshipDAO = (GuardianshipDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.GUARDIANSHIP);

    @Override
    public ArrayList<GuardianshipDTO> getAllGuardians() throws SQLException {
        ArrayList<GuardianshipDTO> allGuardianship = new ArrayList<>();
        ArrayList<GuardianshipDetails> all = queryDAO.getAllGuardianshipDetails();

        for (GuardianshipDetails detail : all) {
            allGuardianship.add(new GuardianshipDTO(
                    detail.getStudentId(),
                    detail.getStudentName(),
                    detail.getGuardianId(),
                    detail.getGuardianName(),
                    detail.getRelation(),
                    detail.getEmergencyContact()

            ));
        }
        return allGuardianship;
    }

    @Override
    public String getEmergencyContact(String selectedStudentId) throws SQLException {
        return guardianshipDAO.getEmergencyContact(selectedStudentId);
    }
}