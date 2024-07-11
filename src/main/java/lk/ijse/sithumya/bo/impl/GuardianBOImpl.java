package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.GuardianBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.GuardianDAO;
import lk.ijse.sithumya.dao.custom.GuardianshipDAO;
import lk.ijse.sithumya.dto.GuardianDTO;
import lk.ijse.sithumya.entity.Guardian;
import lk.ijse.sithumya.entity.Guardianship;
import lk.ijse.sithumya.util.TransactionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuardianBOImpl implements GuardianBO {
    private GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.GUARDIAN);
    private GuardianshipDAO guardianshipDAO = (GuardianshipDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.GUARDIANSHIP);

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

    @Override
    public GuardianDTO searchGuardian(String guardianId) throws SQLException, ClassNotFoundException {
        Guardian guardian = guardianDAO.search(guardianId);

        if (guardian != null) {
            return new GuardianDTO(
                    guardian.getGuardianId(),
                    guardian.getName(),
                    guardian.getRelation(),
                    guardian.getContact(),
                    guardian.getEmail(),
                    guardian.getAddress()
            );
        }
        return null;
    }

    @Override
    public boolean deleteGuardian(String guardianId) throws SQLException, ClassNotFoundException {
        return guardianDAO.delete(guardianId);
    }

    @Override
    public boolean saveGuardianWithGuardianship(GuardianDTO guardianDTO, String studentId, String emergencyContact) throws SQLException, ClassNotFoundException {
        try {
            TransactionUtil.startTransaction();
            Guardian guardian = new Guardian(guardianDTO.getGuardianId(), guardianDTO.getName(), guardianDTO.getRelation(), guardianDTO.getContact(), guardianDTO.getEmail(), guardianDTO.getAddress());
            boolean isSaved = guardianDAO.save(guardian);

            if (isSaved) {
                Guardianship guardianship = new Guardianship(studentId, guardianDTO.getGuardianId(), emergencyContact);
                boolean isGuardianshipSaved = guardianshipDAO.save(guardianship);
                if (isGuardianshipSaved) {
                    TransactionUtil.endTransaction();
                    return true;
                } else {
                    TransactionUtil.rollBack();
                    return false;
                }
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
    public boolean updateGuardianWithGuardianship(GuardianDTO guardianDTO, String studentId, String emergencyContact) throws SQLException, ClassNotFoundException {
        try {
            TransactionUtil.startTransaction();
            Guardian guardian = new Guardian(guardianDTO.getGuardianId(), guardianDTO.getName(), guardianDTO.getRelation(), guardianDTO.getContact(), guardianDTO.getEmail(), guardianDTO.getAddress());
            boolean isUpdated = guardianDAO.update(guardian);

            if (isUpdated) {
                Guardianship guardianship = new Guardianship(studentId, guardianDTO.getGuardianId(), emergencyContact);
                boolean isGuardianshipUpdated = guardianshipDAO.update(guardianship);
                if (isGuardianshipUpdated) {
                    TransactionUtil.endTransaction();
                    return true;
                } else {
                    TransactionUtil.rollBack();
                    return false;
                }
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
}
