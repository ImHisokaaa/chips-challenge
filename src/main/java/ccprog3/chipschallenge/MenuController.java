package ccprog3.chipschallenge;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController {

    @FXML private Button btnStart;
    @FXML private Button btnHelp;
    @FXML private Button btnExit;


    @FXML
    public void initialize() {

        btnStart.setOnMouseClicked(e -> switchToGame());

        btnHelp.setOnMouseClicked(e -> switchToHelp());

        btnExit.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

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