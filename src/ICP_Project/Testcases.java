package ICP_Project;

import java.util.Scanner;
import java.util.ArrayList;

public class Testcases {

    public static ArrayList<Airline> Airline_objects = new ArrayList<>();
    public static ArrayList<Routes> Route_objects = new ArrayList<>();

    public static void Testcase(){
        /**
         * Looping through and converting each array to an object and adding them
         * to the Airline_objects arraylist
         * @param filename
         */
        ArrayList<String[]> Airline_contentArray = new ArrayList<>(Readfile.Readfile("src/airlines.csv"));
        for (String[] array: Airline_contentArray){
            Airline Airlineobject = Objects.AirlineObject(array);
            Airline_objects.add(Airlineobject);
        }
        /**
         * Looping through and converting each array to an object and adding them
         * to the Routes_objects arraylist
         * @param filename
         */
        ArrayList<String[]> Routes_contentArray = new ArrayList<>(Readfile.Readfile("src/routes.csv"));
        for (String[] array: Routes_contentArray){
            Routes Routesobject = Objects.RouteObject(array);
            Route_objects.add(Routesobject);
        }


        //---------------------TEST CASES---------------

        /**
         * Description: Printing out the objects of were the extra comma's can be located in the file
         * This displays the entire record including the ID where the extra comma's are located and
         * apply those ID's in combining two columns
         */
        Objects.Extracommas();



        /**
         * Getting the AirportID given the city and Country name
         */
        System.out.println(Objects.getAirportID("Accra", "Ghana"));
        Scanner input = new Scanner(System.in);
        System.out.println("Enter city name: ");
        String city = input.nextLine();
        System.out.println("Enter country name: ");
        String Country = input.nextLine();
        System.out.print("The Airport ID for "+ city+","+Country+" is: ");
        System.out.print(Objects.getAirportID(city, Country));
        System.out.println();

        /**
         * Getting the Airport object given the AirportID
         */
        System.out.println(Objects.getAirportByCode("GKA"));

        /**
         * Testing the Haversine distance method to get the total distance from a source
         * to a destination in Kilometres
         */
        System.out.println(Haversine.RouteDistance("DME", "UUA"));

        String[] result = Readfile.Readfile("src/routes.csv").get(0);

        Routes res = Objects.RouteObject(result);
        //System.out.println(res.getAirline_code());
        System.out.println(res.populateHashmap());

        System.out.println(res.Routemap);



}

}