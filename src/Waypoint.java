import java.util.LinkedList;
import java.util.List;


public class Waypoint implements Node {
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

    public Waypoint(char name) {
        this(Character.toString(name));
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

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }


    public void setAsUnvisited() {
        isVisited = false;
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
