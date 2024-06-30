package lk.ijse.sithumya.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.StockBO;
import lk.ijse.sithumya.dto.StockDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.StockTm;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryFormController {

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colMaintenanceId;

    @FXML
    private TableView<StockTm> tblStock;

    private StockBO stockBO = (StockBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STOCK);

    @Getter
    private static InventoryFormController controller;

    public InventoryFormController() {
        controller = this;
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToInventoryManageForm();
    }

    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllStocks();
    }

    public void loadAllStocks() {
        tblStock.getItems().clear();
        try {
            ArrayList<StockDTO> allStocks = stockBO.getAllStocks();

            for (StockDTO stockDTO : allStocks) {
                tblStock.getItems().add(new StockTm(
                        stockDTO.getItemId(),
                        stockDTO.getItemName(),
                        stockDTO.getQty(),
                        stockDTO.getCost(),
                        stockDTO.getMaintenanceId()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colMaintenanceId.setCellValueFactory(new PropertyValueFactory<>("maintenanceId"));
    }
}
