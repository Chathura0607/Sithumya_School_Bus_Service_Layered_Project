package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {
    private int paymentId;
    private String studentId;
    private String paymentPlanId;
    private double amount;
    private Date paymentDate;
    private String isCompleted;

    public PaymentDTO(String studentId, String paymentPlanId, double amount, Date paymentDate, String yes) {
        this.studentId = studentId;
        this.paymentPlanId = paymentPlanId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.isCompleted = yes;
    }
}
