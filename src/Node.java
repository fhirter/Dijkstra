import java.util.List;

public interface Node {
    String getName();
    Node getPredecessor();
    void setPredecessor(Node node);
    int getCurrentDistance();
    void setVisited(boolean value);
    void setCurrentDistance(int distance);
    List<Node> getNeighbours();
    boolean isVisited();
    void addNeighbour(Node node);
}
