package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.DashboardDAO;
import lk.ijse.sithumya.dto.TodayPaymentsDTO;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAOImpl implements DashboardDAO {
    @Override
    public int getTotalStudentsCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT COUNT(*) FROM Student");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    @Override
    public int getTotalBusCount() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT COUNT(*) FROM Bus");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    @Override
    public int getTotalDriversCount() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT COUNT(*) FROM Driver");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    @Override
    public String getUserName() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT Name FROM User");
        return resultSet.next() ? resultSet.getString("Name") + "," : null;
    }

    @Override
    public ArrayList getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Object entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Object entity) throws SQLException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return List.of();
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Object search(String id) throws SQLException {
        return null;
    }
}
