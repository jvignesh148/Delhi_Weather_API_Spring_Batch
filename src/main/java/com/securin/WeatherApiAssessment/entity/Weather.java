package com.securin.WeatherApiAssessment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Weather {
    @Id
    private String datetime_utc;
    private String conds;
    private Double dewptm;
    private Double fog;
    private Double hail;
    private Double heatindexm;
    private Double hum;
    private Double pressurem;
    private Double rain;
    private Double snow;
    private Double tempm;
    private Double thunder;
    private Double tornado;
    private Double vism;
    private Double wdird;
    private String wdire;
    private Double wgustm;
    private Double windchillm;
    private Double wspdm;
}
