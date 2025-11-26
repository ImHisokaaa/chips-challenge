package ccprog3.chipschallenge;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controls the main menu interactions.
 * Handles navigation to the game, help screen, or exiting the application.
 */
public class MenuController {

    @FXML private Button btnStart;
    @FXML private Button btnHelp;
    @FXML private Button btnExit;


    /**
     * Sets up the initial state of the menu.
     * Assigns click event handlers to the Start, Help, and Exit buttons.
     */
    @FXML
    public void initialize() {

        btnStart.setOnMouseClicked(e -> switchToGame());

        btnHelp.setOnMouseClicked(e -> switchToHelp());

        btnExit.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

    /**
     * Transitions the window to the main game scene.
     * Loads the game view and sets up keyboard input handling.
     */
    private void switchToGame() {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("game-view.fxml"));
            Scene gameScene = new Scene(fxml.load(), 640, 640);

            Stage stage = (Stage) btnStart.getScene().getWindow();
            stage.setScene(gameScene);

            GameController controller = fxml.getController();
            gameScene.setOnKeyPressed(event -> controller.onKeyPress(event));
            gameScene.getRoot().requestFocus();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Transitions the window to the help screen.
     * Loads the help view FXML.
     */
    private void switchToHelp() {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("help-view.fxml"));
            Scene helpScene = new Scene(fxml.load(), 640, 640);

            Stage stage = (Stage) btnHelp.getScene().getWindow();
            stage.setScene(helpScene);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}