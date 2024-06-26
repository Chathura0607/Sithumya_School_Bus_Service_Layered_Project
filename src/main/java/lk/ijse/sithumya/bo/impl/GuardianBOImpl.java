package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.GuardianBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.GuardianDAO;

import java.sql.SQLException;
import java.util.List;

public class GuardianBOImpl implements GuardianBO {
    private GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.GUARDIAN);

    @Override
    public List<String> getAllGuardianEmails() throws SQLException {
        return guardianDAO.getAllGuardianEmails();
    }
}
