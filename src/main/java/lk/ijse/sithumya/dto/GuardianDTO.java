package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuardianDTO {
    private String guardianId;
    private String name;
    private String relation;
    private String contact;
    private String email;
    private String address;
}
