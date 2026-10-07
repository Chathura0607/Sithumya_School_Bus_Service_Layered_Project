package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.FillingStationBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.FillingStationDAO;
import lk.ijse.sithumya.dto.FillingStationDTO;
import lk.ijse.sithumya.entity.FillingStation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FillingStationBOImpl implements FillingStationBO {
    FillingStationDAO fillingStationDAO = (FillingStationDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.STATION);

    @Override
    public ArrayList<FillingStationDTO> getAllStations() throws SQLException, ClassNotFoundException {
        ArrayList<FillingStationDTO> allStations = new ArrayList<>();
        ArrayList<FillingStation> all = fillingStationDAO.getAll();

        for (FillingStation fillingStation : all) {
            allStations.add(new FillingStationDTO(
                    fillingStation.getId(),
                    fillingStation.getName(),
                    fillingStation.getLocation(),
                    fillingStation.getContact()
            ));
        }
        return allStations;
    }

    @Override
    public List<String> getAllStationIds() throws SQLException, ClassNotFoundException {
        return fillingStationDAO.getAllIds();
    }

    @Override
    public boolean saveStation(FillingStationDTO fillingStationDTO) throws SQLException, ClassNotFoundException {
        return fillingStationDAO.save(new FillingStation(fillingStationDTO.getId(), fillingStationDTO.getName(), fillingStationDTO.getLocation(), fillingStationDTO.getContact()));
    }

    @Override
    public boolean updateStation(FillingStationDTO fillingStationDTO) throws SQLException, ClassNotFoundException {
        return fillingStationDAO.update(new FillingStation(fillingStationDTO.getId(), fillingStationDTO.getName(), fillingStationDTO.getLocation(), fillingStationDTO.getContact()));
    }

    @Override
    public FillingStationDTO searchStation(String stationId) throws SQLException, ClassNotFoundException {
        FillingStation fillingStation = fillingStationDAO.search(stationId);

        if (fillingStation != null) {
            return new FillingStationDTO(
                    fillingStation.getId(),
                    fillingStation.getName(),
                    fillingStation.getLocation(),
                    fillingStation.getContact()
            );
        }
        return null;
    }

    @Override
    public boolean deleteStation(String stationId) throws SQLException, ClassNotFoundException {
        return fillingStationDAO.delete(stationId);
    }

    @Override
    public String generateNextStationId() throws SQLException, ClassNotFoundException {
        return fillingStationDAO.generateNewId();
    }
}
