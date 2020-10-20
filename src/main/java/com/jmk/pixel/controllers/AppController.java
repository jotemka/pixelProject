package com.jmk.pixel.controllers;

import com.jmk.pixel.helpers.Helper;
import com.jmk.pixel.model.ResponseObject;
import com.jmk.pixel.notifications.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/algorithms")
public class AppController {

    @GetMapping(value = "/LuhnControl/{numberAsString}")
    public @ResponseBody
    ResponseEntity getControlDigit(@PathVariable String numberAsString){
        try{
            Integer number = Integer.parseInt(numberAsString);
            if(number<0){
                return new ResponseEntity(ResponseObject.createError(Notification.NEGATIVE_NUMBER), HttpStatus.BAD_REQUEST);
            }

            Integer controlDigit = Helper.calculateControlDigit(number);

            return new ResponseEntity(ResponseObject.createSuccess("Cyfra kontrolna to: " + controlDigit), HttpStatus.OK);

        } catch(NumberFormatException e){
            //handle
            return new ResponseEntity(ResponseObject.createError(Notification.NOT_A_NUMBER), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/LuhnCheck/{numberAsString}")
    public @ResponseBody
    ResponseEntity checkIfNumberCorrect(@PathVariable String numberAsString){
        try{
            Integer number = Integer.parseInt(numberAsString);
            if(number<0){
                return new ResponseEntity(ResponseObject.createError(Notification.NEGATIVE_NUMBER), HttpStatus.BAD_REQUEST);
            }

            boolean correct = Helper.checkIfNumberIsCorrect(number);
            if(correct){
                return new ResponseEntity(ResponseObject.createSuccess("Podany ciąg jest poprawny"), HttpStatus.OK);
            } else {
                return new ResponseEntity(ResponseObject.createSuccess("Podany ciąg jest niepoprawny"), HttpStatus.OK);
            }

        } catch(NumberFormatException e){
            //handle
            return new ResponseEntity(ResponseObject.createError(Notification.NOT_A_NUMBER), HttpStatus.BAD_REQUEST);
        }
    }
}
