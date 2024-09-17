package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name="period_time")
@Setter
@Getter
public class PeriodTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="period_time_id")
    private Integer periodTimeId;

    @Column(name="start_time")
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;

    @Column(name="end_time")
    @Temporal(TemporalType.TIME)
    private LocalTime endTime;
}



























