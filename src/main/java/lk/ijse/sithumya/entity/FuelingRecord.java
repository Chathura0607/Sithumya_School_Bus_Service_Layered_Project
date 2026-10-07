package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuelingRecord {
    private int recordId;
    private String busId;
    private String stationId;
    private double cost;
    private double payment;
    private double debt;
}
