package com.sailpoint.atmdispenser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ApplicationConfig {
    
    @Value("${pinValidationRegex}")
    private String pinValidationRegex;

    @Value("${pinValidationRetry}")
    private int pinValidationRetry;

    @Value("${interface:local}")
    private String interfaceValue;

}
