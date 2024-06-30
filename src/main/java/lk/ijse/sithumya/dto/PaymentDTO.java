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
}
