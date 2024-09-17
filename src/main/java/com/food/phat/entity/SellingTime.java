package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="selling_time")
@Setter
@Getter
public class SellingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selling_time_id")
    private Integer sellingTimeId;

    @Column(name="date_of_week")
    private Integer dateOfWeek;

    @Column(name="valid_from")
    @Temporal(TemporalType.DATE)
    private Date validFrom;

    @Column(name="valid_through")
    @Temporal(TemporalType.DATE)
    private Date validThrough;

    @Column(name="is_active")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="selling_time_fkey", nullable = false)
    private List<PeriodTime> periodTime;
}
