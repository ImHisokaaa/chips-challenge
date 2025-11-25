package ccprog3.chipschallenge;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {

    @FXML private Button btnBack;

    @FXML
    public void initialize() {
        btnBack.setOnMouseClicked(e -> goBackToMenu());
    }

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
