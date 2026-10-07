package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Guardian {
    private String guardianId;
    private String name;
    private String relation;
    private String contact;
    private String email;
    private String address;
}
