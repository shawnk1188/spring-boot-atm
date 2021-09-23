package com.sailpoint.atmdispenser.utils;

import com.sailpoint.atmdispenser.config.ApplicationConfig;
import com.sailpoint.atmdispenser.exception.ValidationException;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class ValidationUtils {

    private final ApplicationConfig applicationConfig;
    
    public boolean validatePin(String pin){
      if(!validateStringRegex(pin,applicationConfig.getPinValidationRegex())){
        System.out.println("Please Enter A Valid 4 Digit PIN:");
        return false;
      }else{
        return true;
      }
    }

    public boolean validateInputPin(String pin) throws ValidationException{
      if(!validateStringRegex(pin, applicationConfig.getPinValidationRegex())){
        throw new ValidationException();
      }else {
        return true;
      }
    }

    public boolean validateStringRegex(String input,String regex){
      return input.matches(regex);
    }
}
