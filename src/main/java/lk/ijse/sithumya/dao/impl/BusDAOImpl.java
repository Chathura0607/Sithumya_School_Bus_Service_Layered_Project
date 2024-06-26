package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.BusDAO;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusDAOImpl implements BusDAO {
    @Override
    public boolean saveBusArrivalTime(String busId, String date, String arrivalTime) throws SQLException {
        return SqlUtil.sql("INSERT INTO BusSchedule (Bus_ID, Date, Time_Type, Schedule_Time) VALUES (?, ?, 'Arrival', ?)", busId, date, arrivalTime);
    }

    @Override
    public boolean saveBusReturnTime(String busId, String date, String returnTime) throws SQLException {
        return SqlUtil.sql("INSERT INTO BusSchedule (Bus_ID, Date, Time_Type, Schedule_Time) VALUES (?, ?, 'Return', ?)", busId, date, returnTime);
    }

    @Override
    public List<String> getAllBusIds() throws SQLException {
        List<String> busIds = new ArrayList<>();
        try (ResultSet resultSet = SqlUtil.sql("SELECT Bus_ID FROM Bus")) {
            while (resultSet.next()) {
                busIds.add(resultSet.getString("Bus_ID"));
            }
        }
        return busIds;
    }
}
