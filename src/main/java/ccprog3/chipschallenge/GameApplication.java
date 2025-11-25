package ccprog3.chipschallenge;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Game;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxml = new FXMLLoader(Launcher.class.getResource("/ccprog3/chipschallenge/game-view.fxml"));
        Scene scene = new Scene(fxml.load(), 640, 640);
        GameController controller = fxml.getController();

        scene.setOnKeyPressed(event -> controller.onKeyPress(event));

        stage.setScene(scene);
        stage.setTitle("Chip's Challenge");
        stage.show();
        scene.getRoot().requestFocus();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
