package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;

import java.sql.SQLException;
import java.util.List;

public interface BusDAO extends CurdDAO {
    boolean saveBusArrivalTime(String busId, String date, String arrivalTime) throws SQLException, SQLException;
    boolean saveBusReturnTime(String busId, String date, String returnTime) throws SQLException;
    List<String> getAllBusIds() throws SQLException;
}
