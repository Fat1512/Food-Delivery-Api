package com.food.phat.entity;


import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="selling_time")
public class SellingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selling_time_id")
    private Integer id;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name="date_of_week")
    private Integer dateOfWeek;

    @Column(name="valid_from")
    private Date validFrom;

    @Column(name="valid_through")
    private Date validThrough;
}
