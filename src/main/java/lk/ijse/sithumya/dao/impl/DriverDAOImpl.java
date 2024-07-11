package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.DriverDAO;
import lk.ijse.sithumya.entity.Driver;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {

    @Override
    public ArrayList<Driver> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Driver> allDrivers = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Driver");
        while (resultSet.next()) {
            Driver driver = new Driver(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8)
            );
            allDrivers.add(driver);
        }
        return allDrivers;
    }

    @Override
    public boolean save(Driver entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("INSERT INTO Driver (Driver_ID, Name, Address, Email, Contact_Number) VALUES (?,?,?,?,?)", entity.getDriverId(), entity.getName(), entity.getAddress(), entity.getEmail(), entity.getContactNumber());
    }

    @Override
    public Driver search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Driver WHERE Driver_ID = ?", id);
        if (resultSet.next()) {
            return new Driver(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Driver entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("UPDATE Driver SET Name = ?, Address = ?, Email = ?, Contact_Number = ? WHERE Driver_ID = ?", entity.getName(), entity.getAddress(), entity.getEmail(), entity.getContactNumber(), entity.getDriverId());
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        List<String> driverIds = new ArrayList<>();
        try (ResultSet resultSet = SqlUtil.sql("SELECT Driver_ID FROM Driver")) {
            while (resultSet.next()) {
                driverIds.add(resultSet.getString("Driver_ID"));
            }
        }
        return driverIds;
    }
}