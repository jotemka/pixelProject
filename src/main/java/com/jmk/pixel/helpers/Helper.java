package com.jmk.pixel.helpers;

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

    private static ArrayList<Integer> splitNumber(Integer number){
        char [] digitsAsChars = String.valueOf(number).toCharArray();

        ArrayList<Integer> split = new ArrayList<>();
        for(int i = 0; i<digitsAsChars.length; i++){
            split.add(Integer.parseInt(String.valueOf(digitsAsChars[i])));
        }

        return split;
    }
}
