package com.food.phat.dto.sellingtime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@Setter
@Getter
public class PeriodTimeDTO {
    @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+00:00")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+00:00")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
}
