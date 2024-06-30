package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeeTm {
    private int feeId;
    private String studentId;
    private String planId;
    private double fee;
    private double discount;
    private double totalAmount;
}
