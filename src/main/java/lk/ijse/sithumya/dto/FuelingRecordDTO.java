package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuelingRecordDTO {
    private int recordId;
    private String busId;
    private String stationId;
    private double cost;
    private double payment;
    private double debt;
}
