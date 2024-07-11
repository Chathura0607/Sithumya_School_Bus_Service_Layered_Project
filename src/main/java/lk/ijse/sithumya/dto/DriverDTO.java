package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DriverDTO {
    private String driverId;
    private String name;
    private String address;
    private String email;
    private String contactNumber;
    private double salary;
    private double payment;
    private double paymentDue;

    public DriverDTO(String driverId, String name, String address, String email, String contact) {
        this.driverId = driverId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contactNumber = contact;
    }

    public DriverDTO(String driverId, double salary, double payment, double paymentDue) {
        this.driverId = driverId;
        this.salary = salary;
        this.payment = payment;
        this.paymentDue = paymentDue;
    }
}
