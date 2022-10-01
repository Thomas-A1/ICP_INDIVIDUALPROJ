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
     * @param location
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
            //ArrayList for the transition cost
            ArrayList<String> transion_cost = new ArrayList<>();
            //ArrayList for the Successor states
            ArrayList<String> successor_states = new ArrayList<>();
            //ArrayList for the sequence that would be returned
            ArrayList<ArrayList<String>> sequence = new ArrayList<>();
            if (key_neighbours.containsKey(Sourcecode)){
                //System.out.println("Checked: "+Sourcecode);
                ArrayList<ArrayList<String>> values =  key_neighbours.get(Sourcecode);
                //Looping through the values ArrayList and adding the transition cost at index 0 and successor state at index 1 to the sequence ArrayList to be returned
                for (ArrayList<String> neigbour : values){
                    transion_cost.add(neigbour.get(0));
                    successor_states.add(neigbour.get(1));
                }
                sequence.add(transion_cost);
                sequence.add(successor_states);
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
        private final String Airportcode;
        private Node parent;
        private double path_cost;

        /**
         * Constructor for the Node class
         * @param airportcode: The Airport code
         * @param parent: The parent node
         * @param path_cost: The cost in moving from one city to another
         */
        public Node(String airportcode, Node parent, double path_cost) {
            this.Airportcode = airportcode;
            this.parent = parent;
            this.path_cost = path_cost;
        }

        /**
         * Overloading Constructor
         * @param airportcode
         */
        public Node(String airportcode){
            this.Airportcode = airportcode;
        }
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
         * @param result: The answer
         */
        public void write_to_file(ArrayList<Node> result) {
            try {
                PrintWriter out = new PrintWriter("output.txt");
                String res = null;
                int i = 0;
                for (i = 1; i < result.size(); i++) {
                    res = (i) + ". from " + result.get(i).parent + " to " + result.get(i).Airportcode;
                    out.write(res + "\n");
                    System.out.println(res);
                }
                out.write("Total Distance: " + result.get(result.size() - 1).path_cost + "KM\n");
                out.write("Total number of flights: " + i);
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
            return Objects.hash(Airportcode); // Overiding the hashcode function to provide same hash value for a specific key and avoiding duplicate keys
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
            System.out.println("Checking.....");
            if (OptimalDistance.goal_test(popped_node.Airportcode, Destinationcity)){
                popped_node.solution_path();
                System.out.println("Yay!! Found a Solution: "+ popped_node.Airportcode);
                return true;
            }
            explored.add(popped_node);
            // System.out.println("Expanding: " + popped_node);
            ArrayList<String> costs = OptimalDistance.actions(popped_node.Airportcode).get(0);
            ArrayList<String> successor = OptimalDistance.actions(popped_node.Airportcode).get(1);
            for (int i = 0; i < costs.size(); i++){
                double old_pathcost = Double.parseDouble(costs.get(i))+popped_node.path_cost;
                Node child = new Node(successor.get(i),popped_node, Double.parseDouble(costs.get(i))+popped_node.path_cost);
                if (! (explored.contains(child) && frontier.contains(child))){
                    frontier.add(child);
                }
                else {
                    if (child.path_cost < old_pathcost){
                        old_pathcost = child.path_cost;
                        child.parent = popped_node.parent;
                        frontier.add(child);
                    }
                }
            }
        }
        return false;
    }
}
