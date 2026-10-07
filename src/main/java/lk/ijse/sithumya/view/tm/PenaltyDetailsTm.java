package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PenaltyDetailsTm {
    private int penaltyId;
    private int feeId;
    private double penaltyAmount;
    private String studentId;
    private String studentName;
}
