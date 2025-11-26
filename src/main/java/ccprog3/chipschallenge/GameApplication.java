package ccprog3.chipschallenge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The main entry point for the Chip's Challenge JavaFX application.
 * Responsible for launching the GUI and loading the initial menu scene.
 */
public class GameApplication extends Application {
    
    /**
     * Starts the JavaFX application stage.
     * Loads the "menu-view.fxml" layout and sets the title of the window.
     *
     * @param stage The primary window (Stage) for this application.
     * @throws IOException If the FXML file resource cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Scene scene = new Scene(fxml.load());
        stage.setScene(scene);
        stage.setTitle("Chip's Challenge");
        stage.show();
    }

}
