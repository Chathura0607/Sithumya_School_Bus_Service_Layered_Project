package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.StockBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.StockDAO;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.dto.GuardianDTO;
import lk.ijse.sithumya.dto.StockDTO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.Guardian;
import lk.ijse.sithumya.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {
    private StockDAO stockDAO = (StockDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.STOCK);

    @Override
    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        ArrayList<StockDTO> allStocks = new ArrayList<>();
        ArrayList<Stock> all = stockDAO.getAll();

        for (Stock stock : all) {
            allStocks.add(new StockDTO(
                    stock.getItemId(),
                    stock.getMaintenanceId(),
                    stock.getItemName(),
                    stock.getQty(),
                    stock.getCost()
            ));
        }
        return allStocks;
    }

    @Override
    public boolean deleteItem(String stockId) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(stockId);
    }

    @Override
    public StockDTO searchItem(String stockId) throws SQLException, ClassNotFoundException {
        Stock stock = stockDAO.search(stockId);

        if (stock != null) {
            return new StockDTO(
                    stock.getItemId(),
                    stock.getMaintenanceId(),
                    stock.getItemName(),
                    stock.getQty(),
                    stock.getCost()
            );
        }
        return null;
    }

    @Override
    public boolean saveItem(StockDTO stockDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Stock(stockDTO.getItemId(), stockDTO.getMaintenanceId(), stockDTO.getItemName(), stockDTO.getQty(), stockDTO.getCost()));
    }

    @Override
    public boolean updateItem(StockDTO stockDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(stockDTO.getItemId(), stockDTO.getMaintenanceId(), stockDTO.getItemName(), stockDTO.getQty(), stockDTO.getCost()));
    }
}
