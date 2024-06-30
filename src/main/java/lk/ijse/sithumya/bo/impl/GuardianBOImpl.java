package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.GuardianBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.GuardianDAO;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.dto.GuardianDTO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.Guardian;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuardianBOImpl implements GuardianBO {
    private GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.GUARDIAN);

    @Override
    public List<String> getAllGuardianEmails() throws SQLException {
        return guardianDAO.getAllGuardianEmails();
    }

    @Override
    public ArrayList<GuardianDTO> getAllGuardians() throws SQLException, ClassNotFoundException {
        ArrayList<GuardianDTO> allGuardians = new ArrayList<>();
        ArrayList<Guardian> all = guardianDAO.getAll();

        for (Guardian guardian : all) {
            allGuardians.add(new GuardianDTO(
                    guardian.getGuardianId(),
                    guardian.getName(),
                    guardian.getRelation(),
                    guardian.getContact(),
                    guardian.getEmail(),
                    guardian.getAddress()
            ));
        }
        return allGuardians;
    }
}
