package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockTm {
    private String itemId;
    private String itemName;
    private double qty;
    private double cost;
    private String maintenanceId;
}
