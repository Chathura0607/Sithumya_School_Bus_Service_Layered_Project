package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeeDTO {
    private int feeId;
    private String studentId;
    private String planId;
    private double monthlyFee;
    private double discount;
    private double totalAmount;
    private Date dueDate;

    public FeeDTO(String studentId, String planId) {
        this.studentId = studentId;
        this.planId = planId;
    }
}
