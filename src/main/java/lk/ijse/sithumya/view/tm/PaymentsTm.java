package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentsTm {
    private int PaymentId;
    private String planId;
    private String studentId;
    private double amount;
    private Date date;
    private String isComplete;
}
