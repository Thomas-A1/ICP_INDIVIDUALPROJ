package ICP_Project;

import java.util.ArrayList;

public class Main {
    /**
     * An arraylist that will contain the Airport objects
     */
    public static ArrayList<Airport> Airport_objects = new ArrayList<>();

    public static void main(String[] args) {

        /**
         * Taking the arraylist returned from reading the file,looping
         * through and converting each array to an object and adding them to
         * the Airport_objects
         * @param filename
         */
        ArrayList<String[]> Airport_contentArray = new ArrayList<>(Readfile.Readfile("src/airports.csv"));
        for (String[] array: Airport_contentArray){
            Airport Airportobject = Objects.Airportobject(array);
            Airport_objects.add(Airportobject);
        }
        /**
         * Populating hashmap and reading input file to obtain the source city and Destination
         * to be passed into the Uniform cost search algorithm
         * Description: two test cases which has different root city and destination city
         */
        Routes.populateHashmap();
        String[] inputs = InputFile.readInput("src/ICP_Project/ZanibaToAccraInputfile.csv");
        System.out.println(Search.UniformCostSearch(inputs[0], inputs[1]));
        /**
         * second test case, You can uncomment to test
         */
        //String[] inputs2 = InputFile.readInput("src/ICP_Project/AccraToWinnipegInputfile.csv");
        //System.out.println(Search.UniformCostSearch(inputs2[0], inputs2[1]));

    }

}
