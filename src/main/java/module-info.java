module ccprog3.chipschallenge {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    opens ccprog3.chipschallenge to javafx.fxml;
    opens ccprog3.chipschallenge.gamefiles to javafx.fxml;
    opens ccprog3.chipschallenge.tiles to javafx.fxml;
    exports ccprog3.chipschallenge;
}