package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuardianshipTm {
    private String studentId;
    private String studentName;
    private String guardianId;
    private String guardianName;
    private String relation;
    private String emergencyContact;
}
