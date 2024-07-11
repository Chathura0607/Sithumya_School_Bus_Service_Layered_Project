package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.FillingStationDAO;
import lk.ijse.sithumya.entity.Bus;
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
        return SqlUtil.sql("INSERT INTO Filling_Station (Station_ID, Name, Location, Contact_Number) VALUES (?,?,?,?)", entity.getId(), entity.getName(), entity.getLocation(), entity.getContact());
    }

    @Override
    public FillingStation search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT Station_ID, Name, Location, Contact_Number FROM Filling_Station WHERE Station_ID = ?", id);
        if (resultSet.next()) {
            return new FillingStation(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("DELETE FROM Filling_Station WHERE Station_ID = ?", id);
    }

    @Override
    public boolean update(FillingStation entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("UPDATE Filling_Station SET Name = ?, Location = ?, Contact_Number = ? WHERE Station_ID = ?", entity.getName(), entity.getLocation(), entity.getContact(), entity.getId());
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT Station_ID FROM Filling_Station ORDER BY Station_ID DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("Station_ID");
            String numericPart = id.replaceAll("\\D", "");
            int newCustomerId = Integer.parseInt(numericPart) + 1;
            return String.format("ST%03d", newCustomerId);
        } else {
            return "ST001";
        }
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