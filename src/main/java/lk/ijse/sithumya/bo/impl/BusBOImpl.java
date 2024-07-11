package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.BusBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.BusDAO;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.dto.ScheduleDTO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.Schedule;
import lk.ijse.sithumya.sendMail.EmailService;
import lk.ijse.sithumya.util.TransactionUtil;

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
        try {
            TransactionUtil.startTransaction();

            Schedule schedule = new Schedule();
            schedule.setBusId(scheduleDTO.getBusId());
            schedule.setDate(scheduleDTO.getDate());
            schedule.setTimeType("ARRIVAL");
            schedule.setScheduleTime(scheduleDTO.getScheduleTime());

            boolean isSaved = busDAO.saveBusArrivalTime(schedule);

            if (isSaved) {
                EmailService.sendBusArrivalEmail(scheduleDTO.getBusId(), scheduleDTO.getScheduleTime().toString());
                TransactionUtil.endTransaction();
            } else {
                TransactionUtil.rollBack();
                throw new SQLException("Failed to save bus arrival time!");
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack();
            throw e;
        } finally {
            TransactionUtil.endTransaction();
        }
        return false;
    }

    @Override
    public boolean saveBusReturnTime(ScheduleDTO scheduleDTO) throws SQLException {
        try {
            TransactionUtil.startTransaction();

            Schedule schedule = new Schedule();
            schedule.setBusId(scheduleDTO.getBusId());
            schedule.setDate(scheduleDTO.getDate());
            schedule.setTimeType("RETURN");
            schedule.setScheduleTime(scheduleDTO.getScheduleTime());

            boolean isSaved = busDAO.saveBusReturnTime(schedule);

            if (isSaved) {
                EmailService.sendBusReturnEmail(scheduleDTO.getBusId(), scheduleDTO.getScheduleTime().toString());
                TransactionUtil.endTransaction();
            } else {
                TransactionUtil.rollBack();
                throw new SQLException("Failed to save bus return time!");
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack();
            throw e;
        } finally {
            TransactionUtil.endTransaction();
        }
        return false;
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
