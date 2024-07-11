package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuardianshipDTO {
    private String studentId;
    private String studentName;
    private String guardianId;
    private String guardianName;
    private String emergencyContact;
    private String relation;
}
