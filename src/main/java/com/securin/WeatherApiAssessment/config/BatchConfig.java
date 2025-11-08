package com.securin.WeatherApiAssessment.config;

import com.securin.WeatherApiAssessment.entity.Weather;
import com.securin.WeatherApiAssessment.service.WeatherItemProcessor;
import com.securin.WeatherApiAssessment.service.WeatherItemReader;
import com.securin.WeatherApiAssessment.service.WeatherItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final WeatherItemReader reader;
    private final WeatherItemProcessor processor;
    private final WeatherItemWriter writer;

    public BatchConfig(WeatherItemReader reader, WeatherItemProcessor processor, WeatherItemWriter writer) {
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }

    //Creating Step
    @Bean
    public Step weatherStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("weatherStep",jobRepository)
                .<Weather, Weather>chunk(10000,platformTransactionManager)
                .reader(reader.reader())
                .processor(processor)
                .writer(writer)
                .transactionManager(platformTransactionManager)
                .build();
    }

    //Job creation
    @Bean
    public Job weatherJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("WeatherJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(weatherStep(jobRepository,platformTransactionManager))
                .build();
    }
}
