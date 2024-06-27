package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusDTO {
    private String busId;
    private String name;
    private String driverId;
    private Date driverAssignedDate;
    private double amountPayToBeStation;

    public BusDTO(String busId, String name, String driverId, Date date) {
        this.busId = busId;
        this.name = name;
        this.driverId = driverId;
        this.driverAssignedDate = date;
    }
}