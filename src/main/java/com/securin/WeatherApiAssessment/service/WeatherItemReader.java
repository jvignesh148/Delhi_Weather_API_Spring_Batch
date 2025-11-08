package com.securin.WeatherApiAssessment.service;

import com.securin.WeatherApiAssessment.config.DoubleNADefaultEditor;
import com.securin.WeatherApiAssessment.entity.Weather;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Map;

@Configuration
public class WeatherItemReader {

    @Value("${batch.file.path}")
    private String filePath;

    public FlatFileItemReader<Weather> reader(){

        FlatFileItemReader<Weather> reader= new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(filePath + "testset1.csv"));
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(","); // csv file data are comma seperated
        delimitedLineTokenizer.setStrict(false); // avoid error for missing columns

        delimitedLineTokenizer.setNames(new String[]{"datetime_utc", " _conds", " _dewptm", " _fog", " _hail", " _heatindexm", " _hum",
                " _pressurem", " _rain", " _snow", " _tempm", " _thunder", " _tornado", " _vism", " _wdird", " _wdire",
                " _wgustm", " _windchillm", " _wspdm"});

        // field set mapping : map csv data to entity object
        BeanWrapperFieldSetMapper<Weather> fieldSetMapper= new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Weather.class);

        // Remove N/A and set the value to NULL
        fieldSetMapper.setCustomEditors(Map.of(Double.class, new DoubleNADefaultEditor()));

        // Line Mapper : combines tokenizer and mapper
        DefaultLineMapper<Weather> lineMapper=new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }
}
