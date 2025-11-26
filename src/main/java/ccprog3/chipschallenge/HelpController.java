package ccprog3.chipschallenge;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controls the Help screen logic.
 * Handles the "Back" button functionality to return to the main menu.
 */
public class HelpController {

    @FXML private Button btnBack;

    /**
     * Initializes the controller after the FXML file is loaded.
     * Sets up the click listener for the back button.
     */
    @FXML
    public void initialize() {
        btnBack.setOnMouseClicked(e -> goBackToMenu());
    }

    /**
     * Navigates the application back to the main menu.
     * Loads the menu scene and updates the stage.
     */
    private void goBackToMenu() {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene menuScene = new Scene(fxml.load(), 640, 640);

            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(menuScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
