package com.jms.jobmarketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobMarketplaceApplication {

    public static void main(String[] args) { SpringApplication.run(JobMarketplaceApplication.class, args); }

}
