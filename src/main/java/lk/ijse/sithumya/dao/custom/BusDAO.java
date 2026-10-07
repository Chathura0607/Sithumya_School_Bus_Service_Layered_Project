package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.Schedule;

import java.sql.SQLException;

public interface BusDAO extends CurdDAO<Bus> {
    boolean saveBusArrivalTime(Schedule schedule) throws SQLException;

    boolean saveBusReturnTime(Schedule schedule) throws SQLException;

    boolean updateDebtAmount(Bus bus) throws SQLException;
}
