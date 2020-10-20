package com.jmk.pixel.helpers;

import com.jmk.pixel.model.GeographicPoint;

import java.util.ArrayList;

public class Helper {

    //obliczanie cyfry kontrolej algorytmem luhna
    public static Integer calculateControlDigit(Integer number){
        int controlNumber = 0;

        ArrayList<Integer> digits = splitNumber(number);

        int position = digits.size();
        for(int i=0; i<digits.size(); i++){
            if(position%2 != 0){
                int doubled = digits.get(i) * 2;
                if(doubled >= 10){
                    int digitSum = 0;
                    digitSum = (doubled % 10) + (doubled / 10);
                    doubled = digitSum;
                }
                digits.set(i, doubled);
            }
            position = position - 1;
        }


        for(Integer i : digits){
            controlNumber += i;
        }
        controlNumber = controlNumber % 10;
        if(controlNumber != 0){
            controlNumber = 10 - controlNumber;
        }

        return controlNumber;
    }

    //sprawdzenie czy ciag liczb jest poprawny
    public static Boolean checkIfNumberIsCorrect(Integer number){
        int sum = 0;

        ArrayList<Integer> digits = splitNumber(number);

        int position = digits.size()-1;
        for(int i=0; i<digits.size(); i++){
            if(position%2 != 0){
                int doubled = digits.get(i) * 2;
                if(doubled >= 10){
                    int digitSum = 0;
                    digitSum = (doubled % 10) + (doubled / 10);
                    doubled = digitSum;
                }
                digits.set(i, doubled);
            }
            position = position - 1;
        }

        for(Integer i : digits){
            sum += i;
        }
        sum = sum%10;
        if(sum==0){
            return true;
        }
        return false;
    }

    public static ArrayList<GeographicPoint> nearestNeighbour(ArrayList<GeographicPoint> pointArrayList){
        ArrayList<GeographicPoint> route = new ArrayList<>();

        return route;
    }

    private static ArrayList<Integer> splitNumber(Integer number){
        char [] digitsAsChars = String.valueOf(number).toCharArray();

        ArrayList<Integer> split = new ArrayList<>();
        for(int i = 0; i<digitsAsChars.length; i++){
            split.add(Integer.parseInt(String.valueOf(digitsAsChars[i])));
        }

        return split;
    }

    public static double distance(double lat1, double lat2, double lon1, double lon2){
        final int R = 6371; //promien ziemi

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        //wykorzystanie metody Haversine
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = R * c * 1000; //konwersja na metry

        distance = Math.pow(distance, 2);
        return  Math.sqrt(distance);
    }
}
