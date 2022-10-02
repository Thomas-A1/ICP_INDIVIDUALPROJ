package ICP_Project;

import java.util.ArrayList;

/**
 * InputFile class that reads a file and picks the source city to
 * the destination
 */
public class InputFile {

    public static String[] readInput(String filename) {

        String[] res = new String[2];
        ArrayList<String[]> cityCountryArray;
        /**
         * Description: Try catch method already fulfilled in the Readfile.java */
        cityCountryArray = new ArrayList<>(Readfile.Readfile(filename));
        /**
         * Looping through the cityCountryArray to get the sourceAirportcode based on the specific city
         */
        for (int i = 0; i < cityCountryArray.size(); i++) {
            String code = Objects.getAirportID(cityCountryArray.get(i)[0], cityCountryArray.get(i)[1]);
            res[i] = code;
        }
        return res;
    }
}
