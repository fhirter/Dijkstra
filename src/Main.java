import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        try {
            Network network = new Network("Distanzen.csv");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            FXController controller = new FXController(network);
            loader.setController(controller);
            loader.load();

            controller.setHandlers();

            stage.setTitle("Dijkstra");

            stage.setScene(new Scene(loader.getRoot()));
            stage.show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Platform.exit();
        }





    }
}
