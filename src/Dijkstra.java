import java.util.*;

public class Dijkstra {
    List<Node> route;
    Node currentNode;
    Network network;
    Set<Node> unvisitedNodes;
    Node startNode;
    Node destinationNode;



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

    Dijkstra(Node startNode, Node destinationNode, Network network) {
        this.startNode = startNode;
        this.destinationNode = destinationNode;
        this.network = network;

        unvisitedNodes = new LinkedHashSet<>();
        route = new LinkedList<>();

        setAllNodesAsUnvisited();
        setDistancesToInfinite();
        setStartNodeDistanceToZero();

        setCurrentNode(startNode);
    }

    private List<Node> calculateRoute() {
        route.clear();

        route.add(destinationNode);

        Node node = destinationNode.getPredecessor();

        while(true) {
            route.add(0,node);
            if(node == startNode) {
                break;
            }
            node = node.getPredecessor();
        }
        return route;
    }

    public List<Node> getRoute() {
        calculateRoute();
        return new LinkedList<>(route);
    }

    public int getDistance() {
        return destinationNode.getCurrentDistance();
        // System.out.printf("The Distance from %s to %s is %d\n",startNode.getName(),destinationNode.getName(),destinationNode.getCurrentDistance());
    }

    public Node getClosestNode() {
        Node min = null;

        Iterator<Node> iterator = unvisitedNodes.iterator();

        while(iterator.hasNext()) {
            Node node = iterator.next();
            if(min == null || min.getCurrentDistance()>node.getCurrentDistance()) {
                min = node;
            }
        }
        return min;
    }

    public void setAllNodesAsUnvisited() {
        final Collection<Node> nodes = network.getNodes();
        unvisitedNodes.addAll(nodes);
        Iterator<Node> iterator = unvisitedNodes.iterator();

        while(iterator.hasNext()) {
            iterator.next().setAsUnvisited();
        }
    }

    public void setDistancesToInfinite() {
        for(Node node: network.getNodes()) {
            node.setCurrentDistance(Integer.MAX_VALUE);
        }
    }

    public void setStartNodeDistanceToZero() {
        startNode.setCurrentDistance(0);

    }

    public void setCurrentNode(Node node) {
        currentNode = node;

    }

    public void calcDistancesToNeighboursOverCurrentNodeAndSetSmaller() {
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

    public void setCurrentAsVisited() {
       currentNode.setAsVisited();
    }

    public void removeCurrentNodeFromUnvisitedNodes() {
        unvisitedNodes.remove(currentNode);
    }

    public boolean isDestinationClosest() {
        if(getClosestNode() == destinationNode) {
            return true;
        } else {
            return false;
        }

    }
}
