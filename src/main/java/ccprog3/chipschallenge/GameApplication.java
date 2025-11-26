package ccprog3.chipschallenge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Scene scene = new Scene(fxml.load());
        stage.setScene(scene);
        stage.setTitle("Chip's Challenge");
        stage.show();
    }

}
