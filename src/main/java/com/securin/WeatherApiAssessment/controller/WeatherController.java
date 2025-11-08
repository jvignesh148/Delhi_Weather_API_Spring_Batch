package com.securin.WeatherApiAssessment.controller;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/batch")
public class WeatherController {

    private JobLauncher jobLauncher;

    private Job weatherJob;

    public WeatherController(JobLauncher jobLauncher, Job weatherJob) {
        this.jobLauncher = jobLauncher;
        this.weatherJob = weatherJob;
    }

    @PostMapping("/upload")
    public String uploadRunner() throws Exception{

        File resource=new ClassPathResource("testset1.csv").getFile();
        if(!resource.exists()){
            return "File does not exist";
        }
        String absolutePath= resource.getAbsolutePath();

        System.out.println("Absoulute path of a file is "+absolutePath);

        // Job Run
        JobParameters jobParameters=new JobParametersBuilder()
                                        .addLong("start at", System.currentTimeMillis())
                                        .toJobParameters();
        jobLauncher.run(weatherJob,jobParameters);
        return "Job Started Successfully";
    }
}
