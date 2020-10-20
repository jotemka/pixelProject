package com.jmk.pixel.helpers;

import com.jmk.pixel.model.GeographicPoint;

import java.util.ArrayList;
import java.util.Collections;

public class Helper {

    //obliczanie cyfry kontrolej algorytmem luhna
    public static Integer calculateControlDigit(Integer number){
        int controlNumber = 0;

        ArrayList<Integer> digits = splitNumber(number);

        //numery pozycji uwzględniają ostatnią cyfrę kontrolną (index 0)
        int position = digits.size();
        for(int i=0; i<digits.size(); i++){
            //jeśli pozycja jest nieparzysta cyfra mnożona jest przez 2
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

        //pozycje uwzględniają wszystkie cyfry, ponieważ indeksy w ArrayList zaczynają sie od 0
        //należy ojdąć 1 aby pozycja zgadzała się z indeksem
        int position = digits.size()-1;
        for(int i=0; i<digits.size(); i++){
            //jeśli pozycja jest nieparzysta cyfra mnożona jest przez 2
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

    //algorytm nearest neighbour
    public static ArrayList<GeographicPoint> nearestNeighbour(ArrayList<GeographicPoint> pointArrayList){
        ArrayList<GeographicPoint> route = new ArrayList<>();

        //przyjmujemy że pierwszy podany punkt jest punktem startowym
        route.add(pointArrayList.get(0));
        pointArrayList.remove(0);

        while(pointArrayList.size()>1){
            ArrayList<Double> distances = new ArrayList<>();
            GeographicPoint lastVisited = route.get(route.size()-1);
            for (GeographicPoint g : pointArrayList) {
                //obliczenie odległości od ostatniego odwiedzonego punktu do pozostałych
                Double currentDistance = distance(lastVisited.getLatitude(), g.getLatitude(),
                        lastVisited.getLongitude(), g.getLongitude());
                distances.add(currentDistance);
            }
            int indexOfMinimalDistance = distances.indexOf(Collections.min(distances));
            //dopisanie najblizszego sąsiada do odwiedzonych punktów (w przypadku identycznych dystansów, arbitralnie wybieramy pierwszy znaleziony)
            route.add(pointArrayList.get(indexOfMinimalDistance));
            //usunięcie odwiedzonego punktu z listy dostępnych punktów
            pointArrayList.remove(indexOfMinimalDistance);
        }

        //w momencie gdy zostaje nam jeden punkt do odwiedzenia nie musimy liczyć odległości
        route.add(pointArrayList.get(0));
        //nie musimy usuwać ostatniego punktu z listy dostępnych ponieważ mamy już całą trasę


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

    public static Double calculateRouteLength(ArrayList<GeographicPoint> route){
        Double routeLength = new Double(0.0);

        //obliczanie długości poszczególnych odcinków trasy i sumowanie ich
        for(int i = 0; i <= route.size()-2; i++){
            GeographicPoint start = route.get(i);
            GeographicPoint end = route.get(i+1);
            double currentDistance = distance(start.getLatitude(), end.getLatitude(),
                    start.getLongitude(), end.getLongitude());
            routeLength += currentDistance;
        }

        return routeLength;
    }

}
