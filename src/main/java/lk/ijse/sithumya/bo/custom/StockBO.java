package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StockBO extends SuperBO {
    ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException;

    boolean deleteItem(String stockId) throws SQLException, ClassNotFoundException;

    StockDTO searchItem(String stockId) throws SQLException, ClassNotFoundException;

    boolean saveItem(StockDTO stockDTO) throws SQLException, ClassNotFoundException;

    boolean updateItem(StockDTO stockDTO) throws SQLException, ClassNotFoundException;
}
