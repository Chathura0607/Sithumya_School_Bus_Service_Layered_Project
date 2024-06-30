package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.SalaryDAO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.Driver;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
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
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
