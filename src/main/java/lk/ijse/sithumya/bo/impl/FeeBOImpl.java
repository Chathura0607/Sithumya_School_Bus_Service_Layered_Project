package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.FeeBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.FeeDAO;
import lk.ijse.sithumya.dto.FeeDTO;
import lk.ijse.sithumya.entity.Fee;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeeBOImpl implements FeeBO {
    private FeeDAO feeDAO = (FeeDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.FEE);

    @Override
    public ArrayList<FeeDTO> getAllFees() throws SQLException, ClassNotFoundException {
        ArrayList<FeeDTO> allFees = new ArrayList<>();
        ArrayList<Fee> all = feeDAO.getAll();

        for (Fee fee : all) {
            allFees.add(new FeeDTO(
                    fee.getFeeId(),
                    fee.getStudentId(),
                    fee.getPlanId(),
                    fee.getMonthlyFee(),
                    fee.getDiscount(),
                    fee.getTotalAmount(),
                    fee.getDueDate()
            ));
        }
        return allFees;
    }

    @Override
    public boolean setPlan(FeeDTO feeDTO) throws SQLException, ClassNotFoundException {
        return feeDAO.update(new Fee(feeDTO.getFeeId(), feeDTO.getStudentId(), feeDTO.getPlanId(), feeDTO.getMonthlyFee(), feeDTO.getDiscount(), feeDTO.getTotalAmount(), feeDTO.getDueDate()));
    }
}
