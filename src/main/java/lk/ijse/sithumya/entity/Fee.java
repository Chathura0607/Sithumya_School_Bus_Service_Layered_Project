package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fee {
    private int feeId;
    private String studentId;
    private String planId;
    private double monthlyFee;
    private double discount;
    private double totalAmount;
    private Date dueDate;
}
