package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PenaltyDetails {
    private int penaltyId;
    private int feeId;
    private double penaltyAmount;
    private String studentId;
    private String studentName;
}
