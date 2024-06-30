package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaintenanceDTO {
    private String maintenanceId;
    private String busId;
    private String description;
    private double cost;
    private Date date;
}
