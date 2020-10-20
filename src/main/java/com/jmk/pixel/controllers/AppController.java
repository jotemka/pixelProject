package com.jmk.pixel.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmk.pixel.helpers.Helper;
import com.jmk.pixel.model.*;
import com.jmk.pixel.notifications.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/algorithms")
public class AppController {

    private static ObjectMapper mapper = new ObjectMapper();

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

    @PostMapping(value = "/nearestNeighbour")
    public @ResponseBody ResponseEntity calculateRoute(@Valid @RequestBody GeographicPointList geoPointList){

        ArrayList<GeographicPoint> points = geoPointList.getGeoPoints();
//        for (GeographicPoint p: points) {
//            System.out.println(p.getName());
//        }
        ArrayList<GeographicPoint> pickedRoute = Helper.nearestNeighbour(points);
        Double routeLength = Helper.calculateRouteLength(pickedRoute);

        ArrayList<RoutePoint> routePoints = new ArrayList<>();
        for (int i = 0; i<pickedRoute.size(); i++){
            RoutePoint point = new RoutePoint(pickedRoute.get(i).getName(), i+1);
            routePoints.add(point);
        }
        Route finalizedRoute = new Route(routePoints, routeLength);

        JsonNode returnData = mapper.valueToTree(finalizedRoute);

        return new ResponseEntity(ResponseObject.createSuccess("Trasa obliczona metodą najbliższego sąsiada.", returnData), HttpStatus.OK);
    }
}
