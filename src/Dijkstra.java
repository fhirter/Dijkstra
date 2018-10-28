import java.util.*;

public class Dijkstra {
    List<Node> route;
    Node currentNode;
    Network network;
    Set<Node> unvisitedNodes;
    Node startNode;
    Node destinationNode;

    public static void main(String[] args) {

        Node startNode;
        Node destinationNode;

        Network network = new Network("Distanzen.csv");

        Scanner sc = new Scanner(System.in);
        System.out.println("Gib den Startknoten an:");
        startNode = network.getNodeByName(sc.nextLine());

        System.out.println("Gib den Endknoten an:");
        destinationNode = network.getNodeByName(sc.nextLine());

        long startTime = System.nanoTime();

        Dijkstra dijkstra = new Dijkstra(startNode,destinationNode, network);

        while(true) {
            dijkstra.calcDistancesToNeighboursOverCurrentNodeAndSetSmaller();
            dijkstra.setCurrentAsVisited();
            dijkstra.removeCurrentNodeFromUnvisitedNodes();
            if(dijkstra.isDestinationClosest()) {
                break;
            }
            dijkstra.setCurrentNode(dijkstra.getClosestNode());
        }

        dijkstra.printDistance();
        dijkstra.printRoute();

        long runtime = System.nanoTime() - startTime;
        System.out.println(runtime/1000000+"ms");
    }



    Dijkstra(Node startNode, Node destinationNode, Network network) {
        this.startNode = startNode;
        this.destinationNode = destinationNode;
        this.network = network;

        unvisitedNodes = new HashSet<Node>();
        route = new LinkedList<Node>();

        setAllNodesAsUnvisited();
        addAllNodesToUnvisited();
        setDistancesToInfinite();
        setStartNodeDistanceToZero();

        setCurrentNode(startNode);
    }

    private List<Node> getRoute() {
        Node node = destinationNode.getPredecessor();

        while(true) {
            route.add(0,node);
            node = node.getPredecessor();
            if(node == startNode) {
                break;
            }

        }
        return route;
    }

    private void printRoute() {
        getRoute();
        System.out.printf("The Route from %s to %s is:",startNode.getName(),destinationNode.getName());
        Iterator<Node> iterator = route.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next().getName());
            if(iterator.hasNext()) {
                System.out.print(" -> ");
            }
        }
        System.out.print("\n");
    }

    private void printDistance() {
        System.out.printf("The Distance from %s to %s is %d \n",startNode.getName(),destinationNode.getName(),destinationNode.getCurrentDistance());
    }


    public void addAllNodesToUnvisited() {
        for (Node node: network.getNodes()) {
            unvisitedNodes.add(node);
        }
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
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
        Iterator<Node> iterator = network.getNodes().iterator();
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
                   // System.out.println(network);
             //   System.out.println(neighbour);
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
       currentNode.setVisited(true);
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
