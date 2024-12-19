package com.example.cqrssamplecodes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = FullyQualifiedBeanNameGenerator.class)
public class CqrsSampleCodesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CqrsSampleCodesApplication.class, args);
    }
}
