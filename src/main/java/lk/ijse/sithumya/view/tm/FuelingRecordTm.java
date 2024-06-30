package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuelingRecordTm {
    private int recordId;
    private String busId;
    private String stationId;
    private double cost;
    private double payment;
    private double debt;
}
