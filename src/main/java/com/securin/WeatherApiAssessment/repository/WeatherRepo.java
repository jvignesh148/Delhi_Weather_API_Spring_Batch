package com.securin.WeatherApiAssessment.repository;

import com.securin.WeatherApiAssessment.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRepo extends JpaRepository<Weather, String> {
}
