package lk.ijse.sithumya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Schedule {
    private int scheduleId;
    private String busId;
    private Date date;
    private String timeType;
    private Time scheduleTime;
}
