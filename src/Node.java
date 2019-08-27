import java.util.List;

public interface Node {
    String getName();

    Node getPredecessor();
    void setPredecessor(Node node);

    int getCurrentDistance();
    void setCurrentDistance(int distance);

    void setAsVisited();
    void setAsUnvisited();
    boolean isVisited();

    List<Node> getNeighbours();
    void addNeighbour(Node node);
}
