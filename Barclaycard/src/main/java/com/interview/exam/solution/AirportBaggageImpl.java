package com.interview.exam.solution;

import com.interview.exam.bean.airportBaggage.*;
import com.interview.exam.constants.AirportBaggageConstants;
import com.interview.exam.solution.algorithm.DijkstraAlgorithm;
import com.interview.exam.solution.algorithm.Graph;
import com.interview.exam.solution.algorithm.Node;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by murugaraj on 4/8/2017.
 */
public class AirportBaggageImpl implements AirportBaggage, AirportBaggageConstants {

    /**
     *
     * @throws Exception
     */
    public void findShortestPathForBagsRoute() throws Exception {
        AirportBaggage airportBaggage = new AirportBaggageImpl();
        try {
            // Read the Input Data(Sections: Conveyor System, Departures, & Bags) from File("BaggageRoutingInput.txt") present in 'resources' directory
            BagsRoutingInputDetailsBean bagsRoutingInputDetailsBean = airportBaggage.getBagsRoutingInputDetails();

            // Identify the Optimized Route for Bags
            List<BaggageRouteBean> baggageRouteBeanList = airportBaggage.optimizedRouteOfBaggage(bagsRoutingInputDetailsBean);

            // Display the Output in the Console
            displayResultInConsole(baggageRouteBeanList);

        } catch (Exception exception_) {
            exception_.printStackTrace();
        }
    }


    /**
     *
     * @param bagsRoutingInputDetailsBean
     * @return List<BaggageRouteBean>
     * @throws Exception
     */
    public List<BaggageRouteBean> optimizedRouteOfBaggage(BagsRoutingInputDetailsBean bagsRoutingInputDetailsBean) throws Exception{

        BaggageRouteBean baggageRouteBean = null;
        List<BaggageRouteBean> optimizedBagRouteList = new ArrayList<BaggageRouteBean>();

        for (BagsBean bagsBean: bagsRoutingInputDetailsBean.getBagsBeanList()) {

            String entryPoint = bagsBean.getEntryPoint();
            String destination = null;

            // Identify the Destination Point/Node
            for (DeparturesBean departuresBean: bagsRoutingInputDetailsBean.getDeparturesBeanList()) {
                if (bagsBean.getFlightId().equalsIgnoreCase(departuresBean.getFlightId())) {
                    destination = departuresBean.getFlightGate();
                    break;
                }
            }
            if (destination == null && bagsBean.getFlightId().equalsIgnoreCase(ARRIVAL_STRING)) destination = BAGGAGE_CLAIM_STRING;

            // Prepare Distinct Nodes in a HashMap and Construct Graph from them
            Map<String, Node> nodesHashMap = new HashMap<String, Node>();
            Graph graph = constructGraph(bagsRoutingInputDetailsBean.getConveyorSystemBeanList(), nodesHashMap);

            // Invoke Shortest Path Calculation Method which is implemented using Dijkstra Algorithm
            Graph resultGraph = DijkstraAlgorithm.calculateShortestPathFromSource(graph, nodesHashMap.get(entryPoint));

            // Setting the Result Values(i.e. BagNumber, OptimizedRoute, and Total Travel Time) to the BaggageRouteBean
            baggageRouteBean = new BaggageRouteBean();
            baggageRouteBean.setBagNumber(bagsBean.getBagNumber());

            Iterator<Node> iterator = resultGraph.getNodes().iterator();
            while (iterator.hasNext()) {
                Node node = iterator.next();
                if(node.getName().equalsIgnoreCase(destination)){
                    for (Node shortestPathNode: node.getShortestPath()) {
                        baggageRouteBean.addOptimizedRoute(shortestPathNode.getName());
                    }
                    // Adding the Destination Node Name as the end of the route
                    baggageRouteBean.addOptimizedRoute(node.getName());
                    baggageRouteBean.setTotalTravelTime(node.getDistance());
                    break;
                }
            }
            // Add each Baggage Object to the List
            optimizedBagRouteList.add(baggageRouteBean);

        }

        return optimizedBagRouteList;
    }

    /**
     *
     * @param conveyorSystemBeanList_
     * @param nodesHashMap_
     * @return Graph
     */
    private Graph constructGraph(List<ConveyorSystemBean> conveyorSystemBeanList_, Map<String, Node> nodesHashMap_) {

        // Identifying Unique Node's Name
        Set<String> uniqueNodeNameSet = new HashSet<String>();
        for(ConveyorSystemBean conveyorSystemBean: conveyorSystemBeanList_) {
            uniqueNodeNameSet.add(conveyorSystemBean.getNode1());
            uniqueNodeNameSet.add(conveyorSystemBean.getNode2());
        }

        // Create each Node Object and Place it in a HashMap to be referred Later
        for (String nodeName : uniqueNodeNameSet) {
            nodesHashMap_.put(nodeName, new Node(nodeName));
        }

        Node node1 = null;
        Node node2 = null;
        // Adding adjacent Node Bi-directionally based on Conveyer System Mapping.
        for (ConveyorSystemBean conveyorSystemBean: conveyorSystemBeanList_) {
            node1 = nodesHashMap_.get(conveyorSystemBean.getNode1());
            node1.addDestination(nodesHashMap_.get(conveyorSystemBean.getNode2()), conveyorSystemBean.getTravelTime());

            node2 = nodesHashMap_.get(conveyorSystemBean.getNode2());
            node2.addDestination(nodesHashMap_.get(conveyorSystemBean.getNode1()), conveyorSystemBean.getTravelTime());
        }

        // Create a new Graph by adding all Nodes that were created before.
        Graph graph = new Graph();
        for (Object value : nodesHashMap_.values()) {
            graph.addNode((Node)value);
        }

        return graph;
    }

    /**
     *
     * @return BagsRoutingInputDetailsBean
     * @throws Exception
     */
    public BagsRoutingInputDetailsBean getBagsRoutingInputDetails() throws Exception {

        BagsRoutingInputDetailsBean bagsRoutingInputDetailsBean = new BagsRoutingInputDetailsBean();
        List<ConveyorSystemBean> conveyorSystemBeanList = new ArrayList<ConveyorSystemBean>();
        List<DeparturesBean> departuresBeanList = new ArrayList<DeparturesBean>();
        List<BagsBean> bagsBeanList = new ArrayList<BagsBean>();

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("BaggageRoutingInput.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line = null;
        String whichSection = null;

        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append("\n");

            // Conveyer System - Input
            if (line.contains("# Section:")) {
                if (line.contains(SECTION_CONVEYER_SYSTEM)) {
                    whichSection = SECTION_CONVEYER_SYSTEM;
                } else if (line.contains(SECTION_DEPARTURES)) {
                    whichSection = SECTION_DEPARTURES;
                } else if (line.contains(SECTION_BAGS)){
                    whichSection = SECTION_BAGS;
                }
                continue;
            }

            if (whichSection != null && whichSection.equalsIgnoreCase(SECTION_CONVEYER_SYSTEM)) {
                conveyorSystemBeanList.add(createConveyorSystemBean(line));
            } else if (whichSection != null && whichSection.equalsIgnoreCase(SECTION_DEPARTURES)) {
                departuresBeanList.add(createDeparturesBean(line));
            } else if (whichSection != null && whichSection.equalsIgnoreCase(SECTION_BAGS)) {
                bagsBeanList.add(createBagsBean(line));
            }

        }

        System.out.println("Input(ContentFromFile):\n"+out.toString());

        bagsRoutingInputDetailsBean.setConveyorSystemBeanList(conveyorSystemBeanList);
        bagsRoutingInputDetailsBean.setDeparturesBeanList(departuresBeanList);
        bagsRoutingInputDetailsBean.setBagsBeanList(bagsBeanList);

        return bagsRoutingInputDetailsBean;
    }

    private ConveyorSystemBean createConveyorSystemBean(String line_) {

        ConveyorSystemBean conveyorSystemBean = new ConveyorSystemBean();

        String[] input = line_.split(" ");
        conveyorSystemBean.setNode1(input[0]);
        conveyorSystemBean.setNode2(input[1]);
        conveyorSystemBean.setTravelTime(Integer.parseInt(input[2]));

        return conveyorSystemBean;
    }

    private DeparturesBean createDeparturesBean(String line_) {

        DeparturesBean departuresBean = new DeparturesBean();

        String[] input = line_.split(" ");
        departuresBean.setFlightId(input[0]);
        departuresBean.setFlightGate(input[1]);
        departuresBean.setDestination(input[2]);
        departuresBean.setFlightTime(input[3]);

        return departuresBean;
    }

    private BagsBean createBagsBean(String line_) {

        BagsBean bagsBean = new BagsBean();

        String[] input = line_.split(" ");
        bagsBean.setBagNumber(input[0]);
        bagsBean.setEntryPoint(input[1]);
        bagsBean.setFlightId(input[2]);

        return bagsBean;
    }


    /**
     *
     * @param baggageRouteBeanList_
     */
    private void displayResultInConsole(List<BaggageRouteBean> baggageRouteBeanList_) {
        // Display Output
        StringBuilder resultLine = null;
        System.out.println("Output:");
        System.out.println("```");
        for (BaggageRouteBean baggageRouteBean: baggageRouteBeanList_) {
            resultLine = new StringBuilder();
            resultLine.append(baggageRouteBean.getBagNumber()).append(SINGLE_SPACE);
            for (String route:baggageRouteBean.getOptimizedRouteList()) {
                resultLine.append(route).append(SINGLE_SPACE);
            }
            resultLine.append(COLON).append(SINGLE_SPACE);
            resultLine.append(baggageRouteBean.getTotalTravelTime());
            System.out.println(resultLine);
        }
        System.out.println("```");

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            new AirportBaggageImpl().findShortestPathForBagsRoute();
        } catch (Exception exception_) {
            exception_.printStackTrace();
        }
    }

}
