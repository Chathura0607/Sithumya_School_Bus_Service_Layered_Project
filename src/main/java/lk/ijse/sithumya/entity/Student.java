package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private String studentId;
    private String name;
    private Date dateOfBirth;
    private String school;
    private double distance;
    private String busId;
    private String planId;
}
