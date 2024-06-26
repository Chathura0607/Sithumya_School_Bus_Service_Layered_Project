package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface BusBO extends SuperBO {
    boolean saveBusArrivalTime(String busId, String date, String arrivalTime) throws SQLException;
    boolean saveBusReturnTime(String busId, String date, String returnTime) throws SQLException;
    List<String> getAllBusIds() throws SQLException;
}
