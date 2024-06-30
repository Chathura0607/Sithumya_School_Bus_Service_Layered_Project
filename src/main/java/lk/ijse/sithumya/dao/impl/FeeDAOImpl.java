package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.FeeDAO;
import lk.ijse.sithumya.entity.Fee;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeeDAOImpl implements FeeDAO {
    @Override
    public ArrayList<Fee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Fee> allFees = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Fee");

        while (resultSet.next()) {
            Fee fee = new Fee(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getDate(7)
            );
            allFees.add(fee);
        }
        return allFees;
    }

    @Override
    public boolean save(Fee entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Fee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Fee entity) throws SQLException, ClassNotFoundException {
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
