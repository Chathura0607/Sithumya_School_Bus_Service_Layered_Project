package lk.ijse.sithumya.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTm {
    private String studentId;
    private String name;
    private Date dateOfBirth;
    private String schoolName;
    private double distanceToSchool;
    private String busId;
}