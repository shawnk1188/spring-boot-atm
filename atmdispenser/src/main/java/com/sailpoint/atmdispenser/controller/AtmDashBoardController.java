package com.sailpoint.atmdispenser.controller;

import java.util.List;

import com.sailpoint.atmdispenser.exception.ValidationException;
import com.sailpoint.atmdispenser.model.UserOption;
import com.sailpoint.atmdispenser.service.DashBoardService;
import com.sailpoint.atmdispenser.utils.ValidationUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/atm")
public class AtmDashBoardController {

    private final DashBoardService dashBoardService;
    private final ValidationUtils validationUtils;
    private boolean isValidPin= false;


    @GetMapping("/validatePin")
    public ResponseEntity<String> validatePin(@RequestParam("pin")String pin) throws ValidationException{
        isValidPin = validationUtils.validateInputPin(pin);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/getUserOptions")
    public ResponseEntity<UserOption> userOptionsToSelect(){
        return genericResponse(dashBoardService.userOptions());
    } 

    @GetMapping("/getAccountInfo")
    public <T> ResponseEntity<T> getAccountInformation(){
        return genericResponse(dashBoardService.accountInfo());
    }


  
    public <T> ResponseEntity<T> genericResponse(List<UserOption> responseList){
        if(isValidPin){
                return (ResponseEntity<T>) ResponseEntity.ok().body(responseList);
            }else{
                return (ResponseEntity<T>) ResponseEntity.ok().body("Please Revalidate You Pin");
            }
    }
}
