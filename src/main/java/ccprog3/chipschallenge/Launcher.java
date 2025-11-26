package ccprog3.chipschallenge;

import javafx.application.Application;

/**
 * The entry point for the application.
 * This class serves as a wrapper to launch the JavaFX game environment.
 */
public class Launcher {
    
    /**
     * The main method that starts the application.
     *
     * @param args Command line arguments passed to the program.
     */
    public static void main(String[] args) {
        Application.launch(GameApplication.class, args);
    }
}
