package ICP_Project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Objects;

public class Search{
    /**
     * Instance Variables
     * The SourceAirportcode as the starting point and the DestinationAirportcode as the destination
     */

    private static String SourceAirportcode;
    private static String DestinationAirportcode;

    /**
     * Constructor for the search class
     */
    public Search(String SourceAirportcode, String DestinationAirportcode) {
        Search.SourceAirportcode = SourceAirportcode;
        Search.DestinationAirportcode = DestinationAirportcode;
    }

    @Override
    public String toString() {
        return "From Source to destination: ";
    }

    /**
     * A Goal test method that checks if a particular location reached
     * while searching through the Hashmap is the expected destination
     * @param location: A specific location reached
     */

    public static boolean goal_test(String location) {
        return false;
    }

    /**
     * Actions method to return the sequence of actions taken to reach a goal
     * @return null (for now)
     */

    public ArrayList<ArrayList<String>> Actions() {
        return null;
    }



    public static class OptimalDistance extends Search{
        private static HashMap<String, ArrayList<ArrayList<String>>> mymap;

        /**
         * Constructor for the OptimalDistance class
         * @param SourceAirportcode: The root or starting Airport code
         * @param DestinationAirportcode: The destination Airport code reached
         * @param mymap: The Hashmap to keep track of the key, value pairs
         */

        public OptimalDistance(String SourceAirportcode, String DestinationAirportcode, HashMap<String, ArrayList<ArrayList<String>>> mymap) {
            super(SourceAirportcode, DestinationAirportcode);
            OptimalDistance.mymap = mymap;
        }

        /**
         * Goal test method to check if a particular destination is the same as the expected destination
         * @param location: The particular point reached in the search tree
         * @param Destination: The goal expected
         * @return boolean
         */

        public static boolean goal_test(String location, String Destination) {
            return location.equals(Destination);
        }


        /**
         * The actions method that will return a string array of the all possible
         * routes it took to get to the destination
         * @param Sourcecode: The Airport source code to be passed
         * @return Actions arraylist
         */
        public static ArrayList<ArrayList<String>> actions(String Sourcecode){
            HashMap<String, ArrayList<ArrayList<String>>> key_neighbours = Routes.Routemap;
            ArrayList<String> transition_costs = new ArrayList<>(); //ArrayList for the transition cost
            ArrayList<String> successor_states = new ArrayList<>(); //ArrayList for the Successor states
            ArrayList<String> Stops = new ArrayList<>(); // ArrayList for the Stops
            ArrayList<String> Airline_codes = new ArrayList<>(); // ArrayList fo Airline codes
            ArrayList<ArrayList<String>> sequence = new ArrayList<>(); //ArrayList for the sequence that would be returned
            if (key_neighbours.containsKey(Sourcecode)){
                ArrayList<ArrayList<String>> values =  key_neighbours.get(Sourcecode);
                for (ArrayList<String> neighbour : values){ //Looping through the values ArrayList and adding the transition cost at index 0 and successor state at index 1 to the sequence ArrayList to be returned
                    /**
                     * Description: Checking for edge cases where it is necessary to catch an IndexOutOfBoundsException
                     */
                    try{
                        transition_costs.add(neighbour.get(0));
                        successor_states.add(neighbour.get(1));
                        Stops.add(neighbour.get(2));
                        Airline_codes.add(neighbour.get(3));
                    }
                    catch(IndexOutOfBoundsException ob){
                        System.out.println(ob.getMessage());
                    }
                }
                sequence.add(transition_costs);
                sequence.add(successor_states);
                sequence.add(Stops);
                sequence.add(Airline_codes);
                return sequence;
            }
            return sequence;
        }
    }

    /**
     * Node class which implements the Comparable interface to have access to the compareTo method
     * for comparing two objects if they're the same
     */
    public static class Node implements Comparable<Search.Node> {
        private String Airportcode;
        private Node parent;
        private double path_cost;
        private String Stops;
        private String Airline_code;

        /**
         * Constructor for the Node class
         * @param airportcode: The Airport code
         * @param parent: The parent node
         * @param path_cost: The cost in moving from one city to another
         */
        public Node(String airportcode, Node parent, double path_cost, String Stops, String airline_code) {
            this.Airportcode = airportcode;
            this.parent = parent;
            this.path_cost = path_cost;
            this.Stops = Stops;
            this.Airline_code = airline_code;
        }

        /**
         * Overloading Constructor and seeting the values of the other parameter to null depending on the datatype
         * @param airportcode: The unique code that identifies an airport
         */
        public Node(String airportcode){
            this(airportcode, null, 0.0, "0", " ");
        }


        /**
         * Description: The solution path for finding the destination
         */
        public void solution_path() {
            ArrayList<Node> result = new ArrayList<>();
            Node final_node = this;
            while(final_node != null) {
                result.add(0,final_node);
                final_node = final_node.parent;
            }
            write_to_file(result);
        }

        /**
         * Description: Method that writes output into a file
         * @param result: The output from one source or root to an expected destination
         */
        public void write_to_file(ArrayList<Node> result) {
            try {
                PrintWriter out = new PrintWriter("Output.txt");
                String writer = null;
                int i = 0;
                out.write("*****THIS IS THE SOLUTION****\n");
                out.write("\n");
                for (i = 1; i < result.size(); i++) {
                    writer = (i) + ". " + result.get(i).Airline_code +" from "  + result.get(i).parent +
                            " to " + result.get(i).Airportcode + " " + result.get(i).Stops + " Stops";
                    out.write(writer + "\n");
                    System.out.println(writer);
                }
                out.write("\nTotal Distance: " + result.get(result.size() - 1).path_cost + "KM\n");
                out.write("Total number of flights: " + (i-1));
                out.write("\nThe Optimality Criteria is the Distance");
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int compareTo(Search.Node o) {
            return Double.compare(this.path_cost, o.path_cost);
        }

        @Override
        public String toString() {
            return ""+ Airportcode;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(Airportcode, node.Airportcode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Airportcode); // Overiding the hashcode method to provide unique hash value for a specific key to avoid duplicate keys
        }
    }

    /**
     * UniformCostSearch - Search algorithm to compute the optimal distance from one source location to its destination
     * @param startcity: The city were the journey or traversal started (root)
     * @param Destinationcity: The city were the journey or traversal ends
     * @return boolean
     */
    public static boolean UniformCostSearch(String startcity, String Destinationcity){
        System.out.println("Performing Uniform cost search from " + startcity + " to " + Destinationcity);
        Node root = new Node(startcity);
        // Frontier as a priorityQueue
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(root);
        HashSet<Node> explored = new HashSet<>();
        System.out.println("Initially, frontier = " + frontier);
        System.out.println("Initially, explored set = " + explored);
        while (frontier.size() > 0){
            Node popped_node = frontier.remove();
            System.out.println("Checking......Currently at the Airport code: " + popped_node.Airportcode);
            if (OptimalDistance.goal_test(popped_node.Airportcode, Destinationcity)){
                System.out.println("Yay!! Found a Solution: "+ popped_node.Airportcode);
                popped_node.solution_path();
                return true;
            }
            explored.add(popped_node);
            ArrayList<ArrayList<String>> actionRes = OptimalDistance.actions(popped_node.Airportcode);
            ArrayList<String> costs = new ArrayList<>();
            ArrayList<String> successor = new ArrayList<>();
            ArrayList<String> stops = new ArrayList<>();
            ArrayList<String> airline = new ArrayList<>();
            /**
             * Description: Checking for extreme cases where the destination to get to
             * is null (i.e. '\N'). This way an IndexOutOfBoundsException will be thrown
             * and needs to be catched.
             */
            try{
                costs = actionRes.get(0);
                successor = actionRes.get(1);
                stops = actionRes.get(2);
                airline = actionRes.get(3);
            }
            catch(NumberFormatException nfe){
                System.out.println(nfe.getMessage());
            }
            catch(IndexOutOfBoundsException ob){
                System.out.println(ob.getMessage());
            }

            for (int i = 0; i < costs.size(); i++){
                double old_pathcost = Double.parseDouble(costs.get(i))+popped_node.path_cost;
                int stops_increment = Integer.parseInt(stops.get(i)) + Integer.parseInt(popped_node.Stops);
                Node child = new Node(successor.get(i), popped_node, Double.parseDouble(costs.get(i)) + popped_node.path_cost, String.valueOf(stops_increment), airline.get(i));
                /**
                 * Description: If the child node is not in the frontier and explored set, then we add it to the frontier (i.e., to avoid redundancies)
                 */
                if (!(explored.contains(child) || frontier.contains(child))){
                    frontier.add(child);
                }
                else {
                    /**
                     * Description: Making the optimality criteria to be the shortest distance
                     */
                    if (child.path_cost < old_pathcost){
                        child.parent = popped_node.parent;
                        explored.remove(child);
                        frontier.add(child);
                    }
                }
            }
        }
        return false;
    }
}
