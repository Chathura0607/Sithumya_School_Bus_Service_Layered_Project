package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodayPayments {
    private int paymentId;
    private String studentName;
    private String paymentPlanName;
    private double paymentAmount;
}
