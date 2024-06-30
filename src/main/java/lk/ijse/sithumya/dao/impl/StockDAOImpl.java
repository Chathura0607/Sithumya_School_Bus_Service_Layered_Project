package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.StockDAO;
import lk.ijse.sithumya.entity.Guardian;
import lk.ijse.sithumya.entity.Stock;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> allStocks = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * FROM Inventory_Item");
        while (resultSet.next()) {
            Stock stock = new Stock(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            );
            allStocks.add(stock);
        }
        return allStocks;
    }

    @Override
    public boolean save(Stock entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("INSERT INTO Inventory_Item (ItemID, Name, Quantity, Purchase_Cost) VALUES (?,?,?,?)", entity.getItemId(), entity.getItemName(), entity.getQty(), entity.getCost());
    }

    @Override
    public Stock search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.sql("SELECT ItemID, Maintenance_ID, Name, Quantity, Purchase_Cost FROM Inventory_Item WHERE ItemID = ?", id);
        if (resultSet.next()) {
            return new Stock(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("DELETE FROM Inventory_Item WHERE ItemID = ?", id);
    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("UPDATE Inventory_Item SET Name = ?, Quantity = ?, Purchase_Cost = ?, Maintenance_ID = ?  WHERE ItemID = ?", entity.getItemName(), entity.getQty(), entity.getCost(), entity.getMaintenanceId(), entity.getItemId());
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
