package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.BusBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.BusDAO;

import java.sql.SQLException;
import java.util.List;

public class BusBOImpl implements BusBO {
    private BusDAO busDAO = (BusDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.BUS);

    @Override
    public boolean saveBusArrivalTime(String busId, String date, String arrivalTime) throws SQLException {
        return busDAO.saveBusArrivalTime(busId, date, arrivalTime);
    }

    @Override
    public boolean saveBusReturnTime(String busId, String date, String returnTime) throws SQLException {
        return busDAO.saveBusReturnTime(busId, date, returnTime);
    }

    @Override
    public List<String> getAllBusIds() throws SQLException {
        return busDAO.getAllBusIds();
    }
}
