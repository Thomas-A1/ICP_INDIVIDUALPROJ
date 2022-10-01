package ICP_Project;

import java.util.ArrayList;
import java.util.HashMap;

public class Routes {
    /**
     * Instance Variables
     */
    private final String Airline_code;
    private final String SourceAirportCode;
    private final String DestinationAirportCode;

    // A static Hashmap that will be populated with key (SourceAirportcode), value(DestinationAiportcode and pathcost) pairs, which would be accessed in Search class
    static HashMap<String, ArrayList<ArrayList<String>>> Routemap = new HashMap<>();


    /**
     * Constructor for the Routes class
     * @param airline_code: The unique code for each airline
     * @param sourceAirportCode: The source airport code that identifies the starting point of the airport taken
     * @param destinationAirportCode: The destination Airport code that uniquely identifies the destination airport reached
     */
    public Routes(String airline_code, String sourceAirportCode, String destinationAirportCode) {
        this.Airline_code = airline_code;
        this.SourceAirportCode = sourceAirportCode;
        this.DestinationAirportCode = destinationAirportCode;
    }

    @Override
    public String toString() {
        return "Routes{" +
                "Airline_code='" + Airline_code + '\'' +
                ", SourceAirportCode='" + SourceAirportCode + '\'' +
                ", DestinationAirportCode='" + DestinationAirportCode + '\'' +
                '}';
    }

    /**
     * Getter method to return the Airline code
     * @return Airline_code
     */
    public String getAirline_code() {
        return Airline_code;
    }

    /**
     * Getter method to return the Source Airport code
     * @return SourceAirportcode
     */
    public String getSourceAirportCode() {
        return SourceAirportCode;
    }

    /**
     * Getter method to return the Destination Airport code
     * @return DestinationAirportCode
     */
    public String getDestinationAirportCode() {
        return DestinationAirportCode;
    }

    /**
     * A method that populates the Hashmap with the keys (SourceAirportCode) and its corresponding values (path costs and DestinationAirportcode)
     * @return Hashmap
     */
    public static HashMap<String, ArrayList<ArrayList<String>>> populateHashmap(){
        ArrayList<String[]> result = Readfile.Readfile("src/routes.csv");
        for (String[] element: result){
            ArrayList<ArrayList<String>> values = new ArrayList<>();
            Routes routeobjects = Objects.RouteObject(element);
            String key = routeobjects.SourceAirportCode;
            ArrayList<String> routeCost = new ArrayList<>();
            double cost = Haversine.RouteDistance(key, routeobjects.getDestinationAirportCode());
            routeCost.add(String.valueOf(cost));
            routeCost.add(routeobjects.getDestinationAirportCode());
            // If the key is already in the hashmap, I add the values in an ArrayList of an ArrayList of strings to it (i.e. extending thr values)
            if (Routemap.containsKey(key)){
                values = Routemap.get(key);
                values.add(routeCost);
                Routemap.put(key, values);
            }
            // Else I insert the key into the hashmap and append its corresponding value
            else{
                values.add(routeCost);
                Routemap.put(key, values);
            }
        }
        return Routemap;
    }

}