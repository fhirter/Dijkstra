import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class Network {
    private final Map<String, Node> nodes;
    private final Map<Node, Map<Node, Integer>> distances = new HashMap<>();

    public Network(String path) throws FileNotFoundException {
        nodes = new HashMap<>();

        generateNetwork(path);
    }

    private void generateNetwork(String path) throws FileNotFoundException {
        List<String[]> links = readCSV(path);

        for(String link[]:links) {
            Node a = generateNode(link[0]);
            Node b = generateNode(link[1]);
            a.addNeighbour(b);
            b.addNeighbour(a);
            Integer distance = Integer.parseInt(link[2]);

            if(distances.get(a) != null) { // entry already exists
                distances.get(a).put(b,distance);
            } else {
                Map<Node, Integer> entry = new HashMap<>();
                entry.put(b, distance);
                distances.put(a, entry);
            }
        }
    }

    /**
     * Search for Node with given name, return if found, else generate a new node and add it to the nodes list
     *
     * @param name Name for the node
     * @return  Node with given name
     */
    private Node generateNode(String name) {
        if (nodes.containsKey(name)) {
            return nodes.get(name);
        } else {
            Node node  = new Waypoint(name);
            nodes.put(name, node);
            return node;
        }
    }

    /**
     * Get a node from the nodes list with given name
     * @param name
     * @return node or null of not found
     */
    public Node getNodeByName(String name) {
        return nodes.get(name);
    }

    /**
     * Get distance between two nodes
     * @param from
     * @param to
     * @return integer distance between node from and node to
     */
    public int getDistance(Node from, Node to) {
        if(from == to) {
            return 0;
        }

        if(distances.containsKey(from) && distances.containsKey(to)) {
            if(distances.get(from).get(to) != null) {
                return distances.get(from).get(to);
            }

            if(distances.get(to).get(from) != null) {
                return distances.get(to).get(from);
            }
        }

        throw new NoSuchElementException();
    }

    public List<Node> getNodes() {
        return new ArrayList<Node>(nodes.values());
    }

    /**
     * Read CSV File with network information
     * File needs to be formattet like: "<Nodename A>, <Nodename B>, <int>"
     *
     * @return List with each entry beeing a size 3 array with the two nodenames and the distance between as entries
     */
    private List<String[]> readCSV(String path) throws FileNotFoundException {
        String csvSeparator = ",";

        BufferedReader br = new BufferedReader(new FileReader(path));

        try {
            String line;
            List<String[]> links = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] link = line.split(csvSeparator); // use comma as separator
                links.add(link);
            }
            return links;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private class Waypoint implements Node {
        private int currentDistance;
        private List<Node> neighbours;
        private String name;
        private boolean isVisited;
        private Node predecessor;

        public Waypoint(String name) {
            currentDistance = Integer.MAX_VALUE;
            neighbours = new LinkedList<>();
            this.name = name;
            isVisited = false;
            predecessor = null;
        }
        public Node getPredecessor() {
            return predecessor;
        }

        public void setPredecessor(Node predecessor) {
            this.predecessor = predecessor;
        }

        public int getCurrentDistance() {
            return currentDistance;
        }

        public void setCurrentDistance(int value) {
            currentDistance = value;
        }

        public List<Node> getNeighbours() {
            return neighbours;
        }

        public void addNeighbour(Node neighbour) {
            this.neighbours.add(neighbour);
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setAsVisited() {
            this.isVisited = true;
        }

        public void setAsUnvisited() {
            this.isVisited = false;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "currentDistance=" + currentDistance +
                    ", name='" + name + '\'' +
                    ", isVisited=" + isVisited +
                    '}';
        }
    }
}