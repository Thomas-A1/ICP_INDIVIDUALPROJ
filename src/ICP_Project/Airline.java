package ICP_Project;

/**
 * @author Thomas Quarshie
 * Creating a class for the Airlines and deriving an instance of it to access its attributes
 */

public class Airline{
    /**
     * Instance Variables
     */
    private final int Airline_id;
    private final String Airline_name;
    private final String alias;
    private final String IATA_code;
    private final String ICAO_code;
    private final String Call_sign;
    private final String Country;
    private final String Active_state;

    /**
     * @param Airline_id: Uniquely identifies an Airline
     * @param alias: The alias of an Airline
     * @param IATA_code: Uniquely identifies the Airport
     * @param ICAO_code: Identifies the region and country the airline is located
     * @param Call_sign: The message transmission for an airline
     * @param Country: The country where the Airline is located
     * @param Active_state: Whether the Airline is active or not
     */
    public Airline(int Airline_id, String Airline_name, String alias, String IATA_code,
                   String ICAO_code, String Call_sign, String Country, String Active_state){

        this.Airline_id = Airline_id;
        this.ICAO_code = ICAO_code;
        this.Airline_name = Airline_name;
        this.alias = alias;
        this.IATA_code = IATA_code;
        this.Call_sign = Call_sign;
        this.Country = Country;
        this.Active_state = Active_state;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Airline{" +
                "Airline_id=" + Airline_id +
                ", Airline_name='" + Airline_name + '\'' +
                ", alias='" + alias + '\'' +
                ", IATA_code='" + IATA_code + '\'' +
                ", ICAO_code='" + ICAO_code + '\'' +
                ", Call_sign='" + Call_sign + '\'' +
                ", Country='" + Country + '\'' +
                ", Active_state='" + Active_state + '\'' +
                '}';
    }

    /**
     * Getter methods for the fields in the Airline class
     * Since the fields are final, there's no need for a mutator method
     * @return Airline ID
     */
    public int getAirline_id(){
        return this.Airline_id;
    }

    /**
     * Getter method to return the Airline_name
     * @return Airline_name
     */
    public String getAirline_name(){
        return this.Airline_name;
    }

    /**
     * Getter method to return the Alias
     * @return alias
     */
    public String getalias(){
        return this.alias;
    }

    /**
     * Getter method to return the IATA_code
     * @return IATA code
     */
    public String IATA_code(){
        return this.IATA_code;
    }

    /**
     * Getter method to return the ICAO code
     * @return ICAO_code
     */
    public String getICAO_code(){
        return this.ICAO_code;
    }

    /**
     * Getter method to return the Call_sign
     * @return Call sign
     */
    public String getCall_sign(){
        return this.Call_sign;
    }

    /**
     * Getter method to return the Country
     * @return Country
     */
    public String getCountry(){
        return this.Country;
    }

    /**
     * Getter method to return the Active_state
     * @return Active_state
     */
    public String getActive_state(){
        return this.Active_state;
    }
}
