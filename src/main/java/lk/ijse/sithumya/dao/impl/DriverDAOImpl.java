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
        return null;
    }

    @Override
    public boolean save(Driver entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Driver search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Driver entity) throws SQLException, ClassNotFoundException {
        return false;
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