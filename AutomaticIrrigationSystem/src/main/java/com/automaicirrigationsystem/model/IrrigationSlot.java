package com.automaicirrigationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "irrigation_slot")
public class IrrigationSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "irrigation_slot_id")
    private Long irrigationSlotId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Plot plot;
    private Date startTime;
    private Date endTime;
    private Integer amountOfWater;
    private String status; // PENDING, IN_PROGRESS, COMPLETED, FAILED
}