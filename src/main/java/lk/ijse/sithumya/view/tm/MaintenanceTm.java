package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaintenanceTm {
    private String maintenanceId;
    private String busId;
    private String description;
    private double cost;
    private Date date;
}
