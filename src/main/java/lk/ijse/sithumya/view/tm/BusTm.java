package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusTm {
    private String busId;
    private String name;
    private String driverId;
    private Date driverAssignedDate;
    private double amountPayToBeStation;
}
