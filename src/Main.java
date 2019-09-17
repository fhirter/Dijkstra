import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Node startNode;
        Node destinationNode;

        Network network = null;
        try {
            network = new Network("Distanzen.csv");
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Gib den Startknoten an:");
        startNode = network.getNodeByName(sc.nextLine());

        System.out.println("Gib den Endknoten an:");
        destinationNode = network.getNodeByName(sc.nextLine());

        long startTime = System.nanoTime();
        Dijkstra dijkstra = new Dijkstra(startNode, destinationNode, network);
        dijkstra.run();
        long runtime = System.nanoTime() - startTime;

        System.out.println(dijkstra.getDistance());
        System.out.println(dijkstra.getRoute());
        System.out.println(runtime / 1000000 + "ms");
    }

  /*  private void buildRouteString() {

    StringBuilder routeString = new StringBuilder();

        routeString.append("The Route from ");
        routeString.append(startNode.getName());
        routeString.append(" to");
        routeString.append(destinationNode.getName());
        routeString.append(" is ");


    Iterator<Node> iterator = route.iterator();
        while(iterator.hasNext())

    {
        routeString.append(iterator.next().getName());
        if (iterator.hasNext()) {
            routeString.append(" -> ");
        }
    }
        return routeString.toString();
}

*/
}
