package lk.ijse.sithumya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleDTO {
    private int scheduleId;
    private String busId;
    private Date date;
    private String timeType;
    private Time scheduleTime;

    public ScheduleDTO(String busId, LocalDate date, LocalTime arrivalTime) {
        this.busId = busId;
        this.date = Date.valueOf(date);
        this.scheduleTime = Time.valueOf(arrivalTime);
    }
}
