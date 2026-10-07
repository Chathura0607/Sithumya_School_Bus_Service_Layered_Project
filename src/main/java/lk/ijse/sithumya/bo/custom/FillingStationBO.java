package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.FillingStationDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FillingStationBO extends SuperBO {
    ArrayList<FillingStationDTO> getAllStations() throws SQLException, ClassNotFoundException;

    List<String> getAllStationIds() throws SQLException, ClassNotFoundException;

    boolean saveStation(FillingStationDTO fillingStationDTO) throws SQLException, ClassNotFoundException;

    boolean updateStation(FillingStationDTO fillingStationDTO) throws SQLException, ClassNotFoundException;

    FillingStationDTO searchStation(String stationId) throws SQLException, ClassNotFoundException;

    boolean deleteStation(String stationId) throws SQLException, ClassNotFoundException;

    String generateNextStationId() throws SQLException, ClassNotFoundException;
}
