package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.FuelingRecordBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.FuelingRecordDAO;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.dto.FuelingRecordDTO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.FuelingRecord;

import java.sql.SQLException;
import java.util.ArrayList;

public class FuelingRecordBOImpl implements FuelingRecordBO {
    FuelingRecordDAO fuelingRecordDAO = (FuelingRecordDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.RECORD);

    @Override
    public ArrayList<FuelingRecordDTO> getAllFuelingRecords() throws SQLException, ClassNotFoundException {
        ArrayList<FuelingRecordDTO> allRecords = new ArrayList<>();
        ArrayList<FuelingRecord> all = fuelingRecordDAO.getAll();

        for (FuelingRecord fuelingRecord : all) {
            allRecords.add(new FuelingRecordDTO(
                    fuelingRecord.getRecordId(),
                    fuelingRecord.getBusId(),
                    fuelingRecord.getStationId(),
                    fuelingRecord.getCost(),
                    fuelingRecord.getPayment(),
                    fuelingRecord.getDebt()
            ));
        }
        return allRecords;
    }
}
