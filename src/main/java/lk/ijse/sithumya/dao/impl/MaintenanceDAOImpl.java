package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.MaintenanceDAO;
import lk.ijse.sithumya.entity.Maintenance;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAOImpl implements MaintenanceDAO {
    @Override
    public ArrayList<Maintenance> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Maintenance> allMaintenance = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Maintenance_Record");
        while (resultSet.next()) {
            Maintenance maintenance = new Maintenance(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDate(5)
            );
            allMaintenance.add(maintenance);
        }
        return allMaintenance;
    }

    @Override
    public boolean save(Maintenance entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("INSERT INTO Maintenance_Record (Maintenance_ID, Bus_ID, Description, Cost, Date) VALUES (?,?,?,?,?)", entity.getMaintenanceId(), entity.getBusId(), entity.getDescription(), entity.getCost(), entity.getDate());
    }

    @Override
    public Maintenance search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT Maintenance_ID, Bus_ID, Description, Cost, Date FROM Maintenance_Record WHERE Maintenance_ID = ?", id);
        if (resultSet.next()) {
            return new Maintenance(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDate(5)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("DELETE FROM Maintenance_Record WHERE Maintenance_ID = ?", id);
    }

    @Override
    public boolean update(Maintenance entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("UPDATE Maintenance_Record SET Bus_ID = ?, Description = ?, Cost = ?, Date = ? WHERE Maintenance_ID = ?", entity.getBusId(), entity.getDescription(), entity.getCost(), entity.getDate(), entity.getMaintenanceId());
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        List<String> maintenanceIds = new ArrayList<>();

        try (ResultSet resultSet = SqlUtil.sql("SELECT Maintenance_ID FROM Maintenance_Record")) {
            while (resultSet.next()) {
                String maintenanceId = resultSet.getString("Maintenance_ID");
                maintenanceIds.add(maintenanceId);
            }
        }
        return maintenanceIds;
    }
}
