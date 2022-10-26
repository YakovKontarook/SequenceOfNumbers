package com.kontarook.sequenceofnumbers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SequenceOfNumbersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SequenceOfNumbersApplication.class, args);
    }

}
