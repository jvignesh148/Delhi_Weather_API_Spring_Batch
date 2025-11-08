package com.securin.WeatherApiAssessment.service;

import com.securin.WeatherApiAssessment.entity.Weather;
import com.securin.WeatherApiAssessment.repository.WeatherRepo;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class WeatherItemWriter implements ItemWriter<Weather> {

    private final WeatherRepo weatherRepo;

    public WeatherItemWriter(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    @Override
    public void write(Chunk<? extends Weather> chunk) throws Exception {
        weatherRepo.saveAll(chunk.getItems());
    }
}
