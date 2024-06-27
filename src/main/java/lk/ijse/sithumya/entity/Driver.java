package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Driver {
    private String driverId;
    private String name;
    private String address;
    private String email;
    private String contactNumber;
    private double salary;
    private double payment;
    private double paymentDue;
}
