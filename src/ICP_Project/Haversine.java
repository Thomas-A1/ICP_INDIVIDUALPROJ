package ICP_Project;

/**
 * @author Jason Winn
 * http://jasonwinn.org
 * Created July 10, 2013
 *
 * Description: Small class that provides approximate distance between
 * two points using the Haversine formula.
 */
public class Haversine {
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }
    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    /**
     * @author: Thomas Kojo Quarshie (ME)
     * @param SourceAirportcode: The starting Airport code
     * @param DestinationAirportcode: The destination Airport code
     * Description: The RouteDistance method that returns the total distance in Kilometres in moving from the SourceAirportcode
     * to the DestinationAirportcode
     * @return double
     */
    public static double RouteDistance(String SourceAirportcode, String DestinationAirportcode) {
        Airport root = Objects.getAirportByCode(SourceAirportcode);
        Airport Destination = Objects.getAirportByCode(DestinationAirportcode);
        double result = Haversine.distance(root.getLatitude(), root.getLongitude(), Destination.getLatitude(), Destination.getLongitude());
        return result;
    }

}
