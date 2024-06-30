package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.FuelingRecordDAO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.FuelingRecord;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelingRecordDAOImpl implements FuelingRecordDAO {
    @Override
    public ArrayList<FuelingRecord> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<FuelingRecord> allRecords = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Fueling_Record");
        while (resultSet.next()) {
            FuelingRecord fuelingRecord = new FuelingRecord(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6)
            );
            allRecords.add(fuelingRecord);
        }
        return allRecords;
    }

    @Override
    public boolean save(FuelingRecord entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public FuelingRecord search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(FuelingRecord entity) throws SQLException, ClassNotFoundException {
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
