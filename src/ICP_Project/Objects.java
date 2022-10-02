package ICP_Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.*;


public class Objects {
    /**
     * Creating an Airport object
     * @return Airport_object
     */
    public static Airport Airportobject(String[] Airportlist) {
        int Airport_id = 0;
        double Latitude = 0.0;
        double Longitude = 0.0;
        double Altitude = 0.0;
        double timezone = 0.0;
        String Airport_name = "";
        String City = "";
        String Country = "";
        String IATA_Code = "";
        String ICAO_Code = "";
        String Data_source = "";
        String Type = "";
        String DST = "";
        String TZ = "";



        /**
         * Tried catching NumberFormatExceptions and handled extreme cases such as column with extra comma's in their records
         * The values you see below (641, 657, 658,...) were obtained by using the getAirportByID method to return records that have their length greater than 14
         * I am commenting the println code out so it doesn't return NumberFormatExceptions in the final Solution (Not to make the work messy)
         */
        if (Airportlist[0].equals(641) || Airportlist[0].equals(657) ||
                Airportlist[0].equals(658) || Airportlist[0].equals(664) ||
                Airportlist[0].equals(3256) || Airportlist[0].equals(3340) ||
                Airportlist[0].equals(4345) || Airportlist[0].equals(4351)
                || Airportlist[0].equals(5582) || Airportlist[0].equals(5583) ||
                Airportlist[0].equals(5589) || Airportlist[0].equals(6898) || Airportlist[0].equals(13714)) {

            try {
                Latitude = Double.parseDouble(Airportlist[6]);
                Longitude = Double.parseDouble(Airportlist[7]);


            } catch (NumberFormatException nfe) {
                //System.out.println("NumberFormatException: " + nfe.getMessage());
            }

            Airport_name = Airportlist[1] + Airportlist[2];
            City = Airportlist[3];
            Country = Airportlist[4];
            IATA_Code = Airportlist[5];
            ICAO_Code = Airportlist[6];
            DST = Airportlist[11];
            TZ = Airportlist[12];
            Type = Airportlist[13];
            Data_source = Airportlist[14];

        } else if (Airportlist[0].equals(5562) || Airportlist[0].equals(5674) ||
                Airportlist[0].equals(5675) || Airportlist[0].equals(5881)) {
            try {
                Airport_id = Integer.parseInt(Airportlist[0]);
                Latitude = Double.parseDouble(Airportlist[6]);
                Longitude = Double.parseDouble(Airportlist[7]);
                Altitude = Double.parseDouble(Airportlist[8]);
                timezone = Double.parseDouble(Airportlist[9]);

            } catch (NumberFormatException nfe) {
                //System.out.println("NumberFormatException: ");
                //nfe.printStackTrace();
            }

            Airport_name = Airportlist[1];
            City =  Airportlist[2] + Airportlist[3];
            Country = Airportlist[4];
            IATA_Code = Airportlist[5];
            ICAO_Code = Airportlist[6];
            DST = Airportlist[11];
            TZ = Airportlist[12];
            Type = Airportlist[13];
            Data_source = Airportlist[14];
        } else {
            try {
                Airport_id = Integer.parseInt(Airportlist[0]);
                Latitude = Double.parseDouble(Airportlist[6]);
                Longitude = Double.parseDouble(Airportlist[7]);
                Altitude = Double.parseDouble(Airportlist[8]);
                timezone = Double.parseDouble(Airportlist[9]);

            } catch (NumberFormatException nfe) {
                //System.out.println("NumberFormatException: " + nfe.getMessage());
            }
            Airport_name = Airportlist[1];
            City = Airportlist[2];
            Country = Airportlist[3];
            IATA_Code = Airportlist[4];
            ICAO_Code = Airportlist[5];
            DST = Airportlist[10];
            TZ = Airportlist[11];
            Type = Airportlist[12];
            Data_source = Airportlist[13];
        }
        return new Airport(Airport_id, Airport_name, City, Country, IATA_Code, ICAO_Code,
                Latitude, Longitude, Altitude, timezone, DST, TZ, Type, Data_source);
    }

    /**
     * Creating an Airline Object
     * @param Airlinelist: Takes a string array
     * @return Airline object
     */
    public static Airline AirlineObject(String[] Airlinelist) {
        int AirlineID = 0;
        try {
            AirlineID = Integer.parseInt(Airlinelist[1]);
        } catch (NumberFormatException nfe) {
            System.out.print("NumberFormatException: " + nfe.getMessage());
        }
        String Airlinename = Airlinelist[1];
        String Alias = Airlinelist[2];
        String IATAcode = Airlinelist[3];
        String ICAOcode = Airlinelist[4];
        String Call_sign = Airlinelist[5];
        String Country = Airlinelist[6];
        String Active_state = Airlinelist[7];

        return new Airline(AirlineID, Airlinename, Alias, IATAcode, ICAOcode,
                Call_sign, Country, Active_state);

    }
    /**
     * Creating a Route Object
     * @param Routelist: Takes a string array
     * @return Routes object
     */
    public static Routes RouteObject(String[] Routelist) {
        int Stops = 0;
        try{
            Stops = Integer.parseInt(Routelist[7]);

        }catch (NumberFormatException nfe){
            nfe.getMessage();
        }
        String Airline_code = Routelist[0];
        String SourceAirportCode = Routelist[2];
        String DestinationAirportCode = Routelist[4];

        return new Routes(Airline_code, SourceAirportCode, DestinationAirportCode, Stops);
    }

    /**
     * Handling the extreme cases by reading the Airports file and returning the records that have their length greater than 14
     * (i.e., have an extra comma in a particular record / tuple)
     */
    public static void Extracommas() {
        BufferedReader reader = null;
        int counter = 0;
        try{
            File file = new File("src/airports.csv");
            reader = new BufferedReader(new FileReader(file));
            String content;
            String[] fieldobjects;
            while ((content = reader.readLine()) != null){
                fieldobjects = content.split(",");
                if (fieldobjects.length > 14) {
                    counter += 1;
                    System.out.print(("The index of an identified extra comma " + counter + " is: ")); // Printing out the records having the extra commas
                    System.out.println(fieldobjects[0]);
                    System.out.println(content);
                    System.out.println();
                }
            }
        }catch(FileNotFoundException fne){
            fne.printStackTrace();
        }catch(IOException ie){
            ie.printStackTrace();
        }
        finally{
            try{
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Method to get the AirportID by passing in a particular city and country
     * @param City: The city of a particular country
     * @param Country: The country to be moved from
     * @return AirportID
     */
    public static String getAirportID(String City, String Country){
        String AirportCode = "";
        for (Airport obj: Main.Airport_objects){
            if (obj.getCity().equals(City) && obj.getCountry().equals(Country)){
                AirportCode = obj.getIATA_Code();
            }
        }return AirportCode;
    }
    /**
     * Method to return an Airport object by comparing to a particular Airport code
     * @param AirportCode: The unique code for a particular airport
     * @return Airport object
     */
    public static Airport getAirportByCode (String AirportCode){
        Airport Airport_objects = new Airport();
        for (Airport airport_obj: Main.Airport_objects){
            if (java.util.Objects.equals(airport_obj.getIATA_Code(), AirportCode)){
                Airport_objects = airport_obj;
            }
        }return Airport_objects;
    }
}