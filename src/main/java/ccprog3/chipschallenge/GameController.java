package ccprog3.chipschallenge;

import ccprog3.chipschallenge.gamefiles.*;
import ccprog3.chipschallenge.tiles.Tile;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controls the game UI, input handling, and the main game loop.
 * Connects the visual interface (FXML) with the game logic classes (Game, Map, Chip).
 */
public class GameController {

    @FXML private Canvas gameCanvas;
    @FXML private Label microchips;
    @FXML private Label blueKeys;
    @FXML private Label redKeys;
    @FXML private Label level;

    private GraphicsContext gc;
    private Game game;
    private Map map;
    private Chip chip;
    private final int TILE_SIZE = 32;
    private long lastEnemyMove = 0;

    /**
     * Initializes the controller after the FXML file is loaded.
     * Sets up the graphics context, loads Level 1, and starts the game loop.
     */
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();

        try {
            loadLevel(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameLoop();
    }

    /**
     * Runs the main game loop using an AnimationTimer.
     * Handles rendering, throttles enemy movement, checks for collisions,
     * and monitors game state (level complete or player death).
     */
    private void gameLoop(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                loadResources();

                if (now - lastEnemyMove > 1_000_000_000L){
                    enemyMovement();
                    lastEnemyMove = now;
                }

                checkEnemyCollision();

                if (game.checkIfComplete()){
                    stop();
                    System.out.println("Level Complete!");
                    loadLevel(game.getLevel());
                    gameLoop();
                    return;
                }

                if (!chip.isAlive()){
                    stop();
                    gameCanvas.getScene().setOnKeyPressed(null); // stops user input
                    return;
                }

            }
        };
        timer.start();
    }

    /**
     * Checks if the player occupies the same tile as any enemy.
     * Kills the player if a collision is detected.
     */
    private void checkEnemyCollision() {
        for (Enemy enemy : map.getEnemies()) {
            if (enemy.getX() == chip.getX() && enemy.getY() == chip.getY()) {
                chip.die("Chip Was Killed");
                return;
            }
        }
    }

    /**
     * Updates the on-screen display (HUD) labels.
     * Shows current counts for microchips, keys, and the level number.
     */
    private void updateHUD(){
        microchips.setText("Chips: " + chip.getInventory().getMicrochips() +
                " / " + map.getChipsNeeded());

        blueKeys.setText("Blue Keys: " + chip.getInventory().getBlueKeys());

        redKeys.setText("Red Keys: " + chip.getInventory().getRedKeys());

        level.setText("Level: " + game.getLevel());
    }

    /**
     * Renders the entire game scene to the canvas.
     * Draws the background, map tiles, player, and enemies.
     */
    private void loadResources(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        for (int y = 0; y < map.getHeight(); y++)
            for (int x = 0; x < map.getWidth(); x++){

                Tile tile = map.getTileAt(x, y);
                gc.drawImage(tile.getImage(), x * TILE_SIZE, y * TILE_SIZE);

            }

        // draw chip
        gc.drawImage(chip.getImage(), chip.getX() * TILE_SIZE, chip.getY() * TILE_SIZE);

        // draw enemy
        for (Enemy e : map.getEnemies()) {
            gc.drawImage(e.getImage(), e.getX() * TILE_SIZE, e.getY() * TILE_SIZE);
        }

        updateHUD();
    }

    /**
     * Handles keyboard input events.
     * Translates W, A, S, D keys into movement commands for the game logic.
     *
     * @param event The key event captured from the scene.
     */
    public void onKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W -> game.charMove('W');
            case A -> game.charMove('A');
            case S -> game.charMove('S');
            case D -> game.charMove('D');
        }
        loadResources();
    }

    /**
     * Iterates through all enemies and triggers their movement logic.
     */
    private void enemyMovement(){
            for (Enemy enemy : map.getEnemies()) {
                enemy.move(map, chip);
            }
    }

    /**
     * Loads a specific level from file and resets game objects.
     * Dynamically resizes the window to fit the new map dimensions.
     *
     * @param level The level number to load.
     */
    public void loadLevel(int level) {
        try {
            String path = "/ccprog3/chipschallenge/levels/level" + level + ".txt";
            map = new Map(path);   // loads next level
            chip = new Chip(map.getStartX(), map.getStartY());
            game = new Game(map, chip, level);
            lastEnemyMove = 0;

            gameCanvas.setWidth(map.getWidth() * TILE_SIZE);
            gameCanvas.setHeight(map.getHeight() * TILE_SIZE);

            if (gameCanvas.getScene() != null) {
                Stage stage = (Stage) gameCanvas.getScene().getWindow();

                // Calculate total window size
                double hudHeight = 50; // approximate height of your HBox HUD
                double width = gameCanvas.getWidth() + 16;  // + window borders
                double height = gameCanvas.getHeight() + hudHeight + 39; // + borders & title bar

                stage.setWidth(width);
                stage.setHeight(height);
            }
        } catch (Exception e) {
            System.out.println("Cannot find level: " + level);
            System.out.println("No more levels. Game finished!");
        }
    }

    public void setGame(Game game) { this.game = game; }
    public void setMap(Map map) { this.map = map; }
    public void setChip(Chip chip) { this.chip = chip; }

}
