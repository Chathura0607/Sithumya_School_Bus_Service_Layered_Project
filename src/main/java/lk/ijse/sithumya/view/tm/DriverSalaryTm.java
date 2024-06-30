package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DriverSalaryTm {
    private String driverId;
    private String name;
    private double salary;
    private double payment;
    private double paymentDue;
}
