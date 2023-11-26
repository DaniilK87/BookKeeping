package com.example.bookkeeping;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BookKeepingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookKeepingApplication.class, args);
    }
}
