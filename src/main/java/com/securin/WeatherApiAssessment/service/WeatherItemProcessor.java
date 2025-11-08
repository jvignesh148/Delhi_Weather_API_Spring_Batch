package com.securin.WeatherApiAssessment.service;

import com.securin.WeatherApiAssessment.entity.Weather;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class WeatherItemProcessor implements ItemProcessor<Weather, Weather> {

    @Override
    public Weather process(Weather weather) throws Exception {
        return weather;
    }
}
