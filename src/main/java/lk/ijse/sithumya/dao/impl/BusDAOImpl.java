package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.BusDAO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.Schedule;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class BusDAOImpl implements BusDAO {

    @Override
    public List<String> getAllIds() throws SQLException {
        List<String> busIds = new ArrayList<>();
        try (ResultSet resultSet = SqlUtil.sql("SELECT Bus_ID FROM Bus")) {
            while (resultSet.next()) {
                busIds.add(resultSet.getString("Bus_ID"));
            }
        }
        return busIds;
    }

    @Override
    public ArrayList<Bus> getAll() throws SQLException {
        ArrayList<Bus> allBus = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Bus");

        while (resultSet.next()) {
            Bus bus = new Bus(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getDouble(5)
            );
            allBus.add(bus);
        }
        return allBus;
    }

    @Override
    public boolean save(Bus entity) throws SQLException {
        return SqlUtil.sql("INSERT INTO Bus (Bus_ID, Name, Driver_ID, Driver_Assigned_Date) VALUES (?,?,?,?)", entity.getBusId(), entity.getName(), entity.getDriverId(), entity.getDriverAssignedDate());
    }

    @Override
    public boolean update(Bus entity) throws SQLException {
        return SqlUtil.sql("UPDATE Bus SET Name = ?, Driver_ID = ?, Driver_Assigned_Date = ? WHERE Bus_ID = ?", entity.getName(), entity.getDriverId(), entity.getDriverAssignedDate(), entity.getBusId());
    }

    @Override
    public String generateNewId() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT Bus_ID FROM Bus ORDER BY Bus_ID DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("Bus_ID");
            String numericPart = id.replaceAll("\\D", "");
            int newCustomerId = Integer.parseInt(numericPart) + 1;
            return String.format("B%03d", newCustomerId);
        } else {
            return "B001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SqlUtil.sql("DELETE b, s FROM Bus b LEFT JOIN Student s ON b.Bus_ID = s.Bus_ID WHERE b.Bus_ID = ?", id);
    }

    @Override
    public Bus search(String id) throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT Bus_ID, Name, Driver_ID, Driver_Assigned_Date, Debt_Amount FROM Bus WHERE Bus_ID = ?", id);
        if (resultSet.next()) {
            return new Bus(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public boolean saveBusReturnTime(Schedule schedule) throws SQLException {
        return SqlUtil.sql("INSERT INTO BusSchedule (Bus_ID, Date, Time_Type, Schedule_Time) VALUES (?, ?, 'Return', ?)", schedule.getBusId(), schedule.getDate(), schedule.getScheduleTime());
    }

    @Override
    public boolean updateDebtAmount(Bus bus) throws SQLException {
        return SqlUtil.sql("UPDATE Bus SET Debt_Amount = COALESCE(Debt_Amount, 0) + ? WHERE Bus_ID = ?", bus.getAmountPayToBeStation(), bus.getBusId());
    }

    @Override
    public boolean saveBusArrivalTime(Schedule schedule) throws SQLException {
        return SqlUtil.sql("INSERT INTO BusSchedule (Bus_ID, Date, Time_Type, Schedule_Time) VALUES (?, ?, 'Arrival', ?)", schedule.getBusId(), schedule.getDate(), schedule.getScheduleTime());
    }
}
