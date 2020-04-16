package com.bym.bankingsystem;

import com.bym.bankingsystem.seeds.ApplicationSeeds;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
public class BankingSystemApplication {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job loanTransaction;

    @Autowired
    Job accountsInterestRate;

    public static void main(String[] args) {
        ConfigurableApplicationContext app =  SpringApplication.run(BankingSystemApplication.class, args);
        ApplicationSeeds applicationSeeds = app.getBean(ApplicationSeeds.class);
        applicationSeeds.init();
    }


    @Scheduled(cron = "0 */1 * * * ?")
    public void performLoanJob() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("loanTransaction", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(loanTransaction, params);
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void performInterestRateJob() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("accountsInterestRate", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(accountsInterestRate, params);
    }

}
