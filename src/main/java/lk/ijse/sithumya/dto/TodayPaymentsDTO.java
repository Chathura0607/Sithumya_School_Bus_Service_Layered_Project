package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodayPaymentsDTO {
    private int paymentId;
    private String studentName;
    private String paymentPlanName;
    private double paymentAmount;
}
