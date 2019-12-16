import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Iterator;
import java.util.List;

public class FXController {
    @FXML private TextField start;
    @FXML private TextField destination;
    @FXML private Button run;
    @FXML private TextField route;

    private final Network network;

    public FXController(Network network) {
        this.network = network;
    }

    public void setHandlers() {
        EventHandler handler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Node startNode;
                Node destinationNode;

                startNode = network.getNode(start.getText());
                destinationNode = network.getNode(destination.getText());

                long startTime = System.nanoTime();
                Dijkstra dijkstra = new Dijkstra(startNode, destinationNode, network);
                dijkstra.run();
                long runtime = System.nanoTime() - startTime;

                String routeString = buildRouteString(dijkstra.getRoute());
                route.setText(routeString);
            }
        };
        run.setOnAction(handler);

    }

    private String buildRouteString(List<Node> route) {

    StringBuilder routeString = new StringBuilder();

        routeString.append("The Route from ");
        routeString.append(route.get(0).getName());
        routeString.append(" to ");
        routeString.append(route.get(route.size()-1).getName());
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
}
