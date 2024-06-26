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
    private Date assignedDate;
    private double debt;
}