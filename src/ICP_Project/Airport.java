package ICP_Project;

/**
 * @author Thomas Quarshie
 * Creating an Airport class and deriving an instance of it to access its attributes
 */
public class Airport {
    /**
     * Instance Variables
     */
    private final int Airport_id;
    private final String Airport_name;
    private final String City;
    private final String Country;
    private final String IATA_Code;
    private final String ICAO_Code;
    private final double Latitude;
    private final double Longitude;
    private final double Altitude;
    private final double timezone;
    private final String DST;
    private final String TZ;
    private final String Type;
    private final String Data_source;


    /**
     * @param Airport_id
     * @param Airport_name
     * @param City
     * @param Country
     * @param IATA_Code
     * @param ICAO_Code
     * @param Latitude
     * @param Longitude
     * @param Altitude
     * @param timezone
     * @param DST
     * @param TZ
     * @param Type
     * @param Data_source
     */
    public Airport(int Airport_id, String Airport_name, String City, String Country,
                   String IATA_Code, String ICAO_Code, double Latitude, double Longitude,
                   double Altitude, double timezone, String DST, String TZ, String Type,
                   String Data_source){

        this.Airport_id = Airport_id;
        this.Airport_name = Airport_name;
        this.City = City;
        this.Country = Country;
        this.IATA_Code = IATA_Code;
        this.ICAO_Code = ICAO_Code;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Altitude = Altitude;
        this.timezone = timezone;
        this.DST = DST;
        this.TZ = TZ;
        this.Type = Type;
        this.Data_source = Data_source;
    }

    public Airport(){
        this.Airport_id = 0;
        this.Airport_name = "";
        this.City = "";
        this.Country = "";
        this.IATA_Code = "";
        this.ICAO_Code = "";
        this.Latitude = 0.0;
        this.Longitude = 1777.0;
        this.Altitude = 1.0;
        this.timezone = 1.0;
        this.DST = "";
        this.TZ = "";
        this.Type = "";
        this.Data_source = "";
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Airport{" +
                "Airport_id=" + Airport_id +
                ", Aiport_name='" + Airport_name + '\'' +
                ", City='" + City + '\'' +
                ", Country='" + Country + '\'' +
                ", IATA_Code='" + IATA_Code + '\'' +
                ", ICAO_Code='" + ICAO_Code + '\'' +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Altitude=" + Altitude +
                ", timezone=" + timezone +
                ", DST='" + DST + '\'' +
                ", TZ='" + TZ + '\'' +
                ", Type='" + Type + '\'' +
                ", Data_source='" + Data_source + '\'' +
                '}';
    }
    public int getAirport_id(){
        return this.Airport_id;
    }

    public String getAiport_name(){
        return this.Airport_name;
    }

    public String getCity(){
        return this.City;
    }

    public String getCountry() {
        return this.Country;
    }

    public String getIATA_Code() {
        return this.IATA_Code;
    }

    public double getLatitude() {
        return this.Latitude;
    }

    public double getLongitude() {
        return this.Longitude;
    }

    public double getAltitude() {
        return this.Altitude;
    }

    public double getTimezone() {
        return this.timezone;
    }
    public String getDST() {
        return this.DST;
    }

    public String getICAO_Code() {
        return this.ICAO_Code;
    }

    public String getType() {
        return this.Type;
    }

    public String getTZ() {
        return this.TZ;
    }

    public String getData_source() {
        return Data_source;
    }
}