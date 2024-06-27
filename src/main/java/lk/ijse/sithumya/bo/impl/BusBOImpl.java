package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.BusBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.BusDAO;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.dto.ScheduleDTO;
import lk.ijse.sithumya.entity.Bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusBOImpl implements BusBO {
    private BusDAO busDAO = (BusDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.BUS);

    @Override
    public List<String> getAllBusIds() throws SQLException, ClassNotFoundException {
        return busDAO.getAllIds();
    }

    @Override
    public ArrayList<BusDTO> getAllBus() throws SQLException, ClassNotFoundException {
        ArrayList<BusDTO> allBus = new ArrayList<>();
        ArrayList<Bus> all = busDAO.getAll();

        for (Bus bus : all) {
            allBus.add(new BusDTO(
                    bus.getBusId(),
                    bus.getName(),
                    bus.getDriverId(),
                    bus.getDriverAssignedDate(),
                    bus.getAmountPayToBeStation()
            ));
        }
        return allBus;
    }

    @Override
    public boolean saveBus(BusDTO busDTO) throws SQLException, ClassNotFoundException {
        return busDAO.save(new Bus(busDTO.getBusId(), busDTO.getName(), busDTO.getDriverId(), busDTO.getDriverAssignedDate(), busDTO.getAmountPayToBeStation()));
    }

    @Override
    public String generateNextBusId() throws SQLException, ClassNotFoundException {
        return busDAO.generateNewId();
    }

    @Override
    public boolean saveBusArrivalTime(ScheduleDTO scheduleDTO) throws SQLException {
        return busDAO.saveBusArrivalTime(scheduleDTO.getBusId(), scheduleDTO.getDate(), scheduleDTO.getScheduleTime());
    }

    @Override
    public boolean saveBusReturnTime(ScheduleDTO scheduleDTO) throws SQLException {
        return busDAO.saveBusReturnTime(scheduleDTO.getBusId(), scheduleDTO.getDate(), scheduleDTO.getScheduleTime());
    }

    @Override
    public BusDTO searchBus(String busId) throws SQLException, ClassNotFoundException {
        Bus bus = busDAO.search(busId);

        if (bus != null) {
            return new BusDTO(
                    bus.getBusId(),
                    bus.getName(),
                    bus.getDriverId(),
                    bus.getDriverAssignedDate(),
                    bus.getAmountPayToBeStation()
            );
        }
        return null;
    }

    @Override
    public boolean deleteBus(String busId) throws SQLException, ClassNotFoundException {
        return busDAO.delete(busId);
    }

    @Override
    public boolean updateBus(BusDTO busDTO) throws SQLException, ClassNotFoundException {
        return busDAO.update(new Bus(busDTO.getBusId(), busDTO.getName(), busDTO.getDriverId(), busDTO.getDriverAssignedDate(), busDTO.getAmountPayToBeStation()));
    }
}
