package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentPlanDTO {
    private String planId;
    private String planName;
    private int numberOfInstallments;
}
