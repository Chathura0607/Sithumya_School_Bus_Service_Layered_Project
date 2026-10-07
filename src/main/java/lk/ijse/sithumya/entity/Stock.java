package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Stock {
    private String itemId;
    private String maintenanceId;
    private String itemName;
    private double qty;
    private double cost;
}
