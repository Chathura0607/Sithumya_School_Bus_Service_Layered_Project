package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.PenaltyBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.QueryDAO;
import lk.ijse.sithumya.dto.PenaltyDetailsDTO;
import lk.ijse.sithumya.entity.PenaltyDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class PenaltyBOImpl implements PenaltyBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.QUERY);

    @Override
    public ArrayList<PenaltyDetailsDTO> getAllPenalties() throws SQLException {
        ArrayList<PenaltyDetailsDTO> allPenalties = new ArrayList<>();
        ArrayList<PenaltyDetails> all = queryDAO.loadAllPenalties();

        for (PenaltyDetails penaltyDetails : all) {
            allPenalties.add(new PenaltyDetailsDTO(
                    penaltyDetails.getPenaltyId(),
                    penaltyDetails.getFeeId(),
                    penaltyDetails.getPenaltyAmount(),
                    penaltyDetails.getStudentId(),
                    penaltyDetails.getStudentName()
            ));
        }
        return allPenalties;
    }
}
