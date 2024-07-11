package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.FuelingRecordBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.BusDAO;
import lk.ijse.sithumya.dao.custom.FuelingRecordDAO;
import lk.ijse.sithumya.dto.FuelingRecordDTO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.FuelingRecord;
import lk.ijse.sithumya.util.TransactionUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class FuelingRecordBOImpl implements FuelingRecordBO {
    FuelingRecordDAO fuelingRecordDAO = (FuelingRecordDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.RECORD);
    BusDAO busDAO = (BusDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.BUS);

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

    @Override
    public boolean isRecordSaved(FuelingRecordDTO fuelingRecordDTO) throws SQLException, ClassNotFoundException {
        try {
            TransactionUtil.startTransaction();

            boolean isRecordSaved = fuelingRecordDAO.save(new FuelingRecord(0,
                    fuelingRecordDTO.getBusId(),
                    fuelingRecordDTO.getStationId(),
                    fuelingRecordDTO.getCost(),
                    fuelingRecordDTO.getPayment(),
                    fuelingRecordDTO.getDebt()
            ));

            Bus bus = new Bus();
            bus.setBusId(fuelingRecordDTO.getBusId());
            bus.setAmountPayToBeStation(fuelingRecordDTO.getDebt());
            boolean isDebtUpdate = busDAO.updateDebtAmount(bus);

            if (isRecordSaved && isDebtUpdate) {
                TransactionUtil.endTransaction();
                return true;
            } else {
                TransactionUtil.rollBack();
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            TransactionUtil.rollBack();
            throw e;
        } finally {
            TransactionUtil.endTransaction();
        }
    }
}
