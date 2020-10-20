package com.jmk.pixel.helpers;

import java.util.ArrayList;

public class Helper {

    //obliczanie cyfry kontrolej algorytmem luhna
    public static Integer calculateControlDigit(Integer number){
        int controlNumber = 0;
        char [] digitsAsChars = String.valueOf(number).toCharArray();

        ArrayList<Integer> digits = new ArrayList<>();
        for(int i = 0; i<digitsAsChars.length; i++){
            digits.add(Integer.parseInt(String.valueOf(digitsAsChars[i])));
        }

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
}
