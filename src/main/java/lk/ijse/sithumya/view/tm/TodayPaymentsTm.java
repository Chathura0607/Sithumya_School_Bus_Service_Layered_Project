package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodayPaymentsTm {
    private int paymentId;
    private String studentName;
    private String paymentPlanName;
    private double paymentAmount;
}