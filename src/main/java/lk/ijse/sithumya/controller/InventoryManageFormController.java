package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.MaintenanceBO;
import lk.ijse.sithumya.bo.custom.StockBO;
import lk.ijse.sithumya.dto.StockDTO;
import lk.ijse.sithumya.entity.Stock;
import lk.ijse.sithumya.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class InventoryManageFormController {

    @FXML
    private ComboBox<String> cmbMaintenanceId;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    private StockBO stockBO = (StockBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STOCK);
    private MaintenanceBO maintenanceBO = (MaintenanceBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.MAINTENANCE);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String stockId = txtItemId.getText();

        try {
            StockDTO stock = stockBO.searchItem(stockId);

            if (stock != null) {
                boolean isDeleted = stockBO.deleteItem(stockId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted Successfully!").show();
                    clearFields();
                    InventoryFormController.getController().initialize();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Item Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String name = txtName.getText();
        double qty = Double.parseDouble(txtQty.getText());
        double cost = Double.parseDouble(txtCost.getText());

        if (isTextValid()) {

            try {
                boolean isSaved = stockBO.saveItem(new StockDTO(itemId, null, name, qty, cost));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Saved Successfully!").show();
                    clearFields();
                    InventoryFormController.getController().initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();

        try {
            StockDTO stock = stockBO.searchItem(itemId);

            if(stock != null) {
                fillFields(stock);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Item Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String name = txtName.getText();
        double qty = Double.parseDouble(txtQty.getText());
        double cost = Double.parseDouble(txtCost.getText());
        String maintenanceId = cmbMaintenanceId.getValue();

        if (isTextValid()) {

            try {
                boolean isUpdated = stockBO.updateItem(new StockDTO(itemId, maintenanceId, name, qty, cost));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Update Successfully!").show();
                    clearFields();
                    InventoryFormController.getController().initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void txtCostOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtCost);
    }

    @FXML
    void txtIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.ITEMId, txtItemId);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
    }

    @FXML
    void txtQtyOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.QTY, txtQty);
    }

    private void clearFields() {
        txtItemId.setText("");
        txtCost.setText("");
        txtQty.setText("");
        txtName.setText("");
        cmbMaintenanceId.getSelectionModel().clearSelection();
    }

    private void fillFields(StockDTO stock) {
        txtItemId.setText(stock.getItemId());
        txtName.setText(stock.getItemName());
        txtQty.setText(String.valueOf(stock.getQty()));
        txtCost.setText(String.valueOf(stock.getCost()));
        cmbMaintenanceId.setValue(stock.getMaintenanceId());
    }

    @FXML
    private void initialize() {
        refreshMaintenanceIds();
    }

    private void refreshMaintenanceIds() {
        try {
            List<String> maintenanceIds = maintenanceBO.getAllMaintenanceIds();
            cmbMaintenanceId.getItems().clear();
            cmbMaintenanceId.getItems().addAll(maintenanceIds);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public boolean isTextValid() {
        boolean isCostValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtCost);
        boolean isItemIdValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.ITEMId, txtItemId);
        boolean isNameValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
        boolean isQtyValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.QTY, txtQty);

        return isCostValid && isItemIdValid && isNameValid && isQtyValid;
    }
}
