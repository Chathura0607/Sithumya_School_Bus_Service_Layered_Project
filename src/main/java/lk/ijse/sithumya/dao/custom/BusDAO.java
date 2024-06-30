package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.Bus;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface BusDAO extends CurdDAO<Bus> {
    boolean saveBusArrivalTime(String busId, Date date, Time scheduleTime) throws SQLException;

    boolean saveBusReturnTime(String busId, Date date, Time scheduleTime) throws SQLException;
}
