package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.FillingStationDAO;
import lk.ijse.sithumya.entity.FillingStation;
import lk.ijse.sithumya.entity.Guardian;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FillingStationDAOImpl implements FillingStationDAO {
    @Override
    public ArrayList<FillingStation> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<FillingStation> allStations = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Filling_Station");
        while (resultSet.next()) {
            FillingStation fillingStation = new FillingStation(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            allStations.add(fillingStation);
        }
        return allStations;
    }

    @Override
    public boolean save(FillingStation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public FillingStation search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(FillingStation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        List<String> stationIds = new ArrayList<>();
        try (ResultSet resultSet = SqlUtil.sql("SELECT Station_ID FROM Filling_Station")) {
            while (resultSet.next()) {
                stationIds.add(resultSet.getString("Station_ID"));
            }
        }
        return stationIds;
    }
}