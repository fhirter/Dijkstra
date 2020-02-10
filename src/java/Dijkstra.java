import java.util.*;

public class Dijkstra {
    private final Network network;
    private List<Node> route;
    private Node currentNode;
    private Set<Node> unvisitedNodes;
    private Node startNode;
    private Node destinationNode;



    Dijkstra(Node startNode, Node destinationNode, Network network) {
        this.startNode = startNode;
        this.destinationNode = destinationNode;
        this.network = network;

        unvisitedNodes = new LinkedHashSet<>();


        setAllNodesAsUnvisited();
        setDistancesToInfinite();
        setStartNodeDistanceToZero();

        setCurrentNode(startNode);
    }

    public void run() {
        while(true) {
            calcDistancesToNeighboursOverCurrentNodeAndSetSmaller();
            setCurrentAsVisited();
            removeCurrentNodeFromUnvisitedNodes();
            if(isDestinationClosest()) {
                break;
            }
            setCurrentNode(getClosestNode());
        }
    }

    public List<Node> getRoute() {
        calculateRoute();
        return new LinkedList<>(route);
    }

    public int getDistance() {
        return destinationNode.getCurrentDistance();
        // System.out.printf("The Distance from %s to %s is %d\n",startNode.getName(),destinationNode.getName(),destinationNode.getCurrentDistance());
    }

    private void calculateRoute() {
        route = new LinkedList<>();

        route.add(destinationNode);

        Node node = destinationNode.getPredecessor();

        while(true) {
            route.add(0,node);
            if(node == startNode) {
                break;
            }
            node = node.getPredecessor();
        }
    }

    private Node getClosestNode() {
        Node min = null;

        for (Node node : unvisitedNodes) {
            if (min == null || min.getCurrentDistance() > node.getCurrentDistance()) {
                min = node;
            }
        }
        return min;
    }

    private void setAllNodesAsUnvisited() {
        final Collection<Node> nodes = network.getNodes();
        unvisitedNodes.addAll(nodes);

        for (Node unvisitedNode : unvisitedNodes) {
            unvisitedNode.setAsUnvisited();
        }
    }

    private void setDistancesToInfinite() {
        for(Node node: network.getNodes()) {
            node.setCurrentDistance(Integer.MAX_VALUE);
        }
    }

    private void setStartNodeDistanceToZero() {
        startNode.setCurrentDistance(0);

    }

    private void setCurrentNode(Node node) {
        currentNode = node;

    }

    private void calcDistancesToNeighboursOverCurrentNodeAndSetSmaller() {
        int newCurrentDistance;
        for(Node neighbour : currentNode.getNeighbours()) {
            if(neighbour != null || !neighbour.isVisited()) {
                try {
                    int distanceToNextNode = network.getDistance(currentNode,neighbour);
                    int currentDistance = currentNode.getCurrentDistance();

                    newCurrentDistance =  currentDistance + distanceToNextNode;

                    if (neighbour.getCurrentDistance() > newCurrentDistance) {
                        neighbour.setCurrentDistance(newCurrentDistance);
                        neighbour.setPredecessor(currentNode);
                    }
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
            }
        }
    }

    private void setCurrentAsVisited() {
       currentNode.setAsVisited();
    }

    private void removeCurrentNodeFromUnvisitedNodes() {
        unvisitedNodes.remove(currentNode);
    }

    private boolean isDestinationClosest() {
        return getClosestNode() == destinationNode;
    }
}
