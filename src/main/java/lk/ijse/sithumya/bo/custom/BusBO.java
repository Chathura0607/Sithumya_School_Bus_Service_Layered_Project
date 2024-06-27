package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.dto.ScheduleDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BusBO extends SuperBO {
    List<String> getAllBusIds() throws SQLException, ClassNotFoundException;

    ArrayList<BusDTO> getAllBus() throws SQLException, ClassNotFoundException;

    boolean saveBus(BusDTO busDTO) throws SQLException, ClassNotFoundException;

    BusDTO searchBus(String busId) throws SQLException, ClassNotFoundException;

    boolean deleteBus(String busId) throws SQLException, ClassNotFoundException;

    boolean updateBus(BusDTO busDTO) throws SQLException, ClassNotFoundException;

    String generateNextBusId() throws SQLException, ClassNotFoundException;

    boolean saveBusArrivalTime(ScheduleDTO scheduleDTO) throws SQLException;

    boolean saveBusReturnTime(ScheduleDTO scheduleDTO) throws SQLException;
}
