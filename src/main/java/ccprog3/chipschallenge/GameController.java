package ccprog3.chipschallenge;

import ccprog3.chipschallenge.gamefiles.*;
import ccprog3.chipschallenge.tiles.Tile;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.lang.classfile.Label;

public class GameController {

    @FXML private Canvas gameCanvas;
    @FXML private Label microchips;
    @FXML private Label keys;

    private GraphicsContext gc;
    private Game game;
    private Map map;
    private Chip chip;
    private final int TILE_SIZE = 32;
    private long lastEnemyMove = 0;

    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();

        try {
            map = new Map("level1.txt");
            chip = new Chip(map.getStartX(), map.getStartY());
            game = new Game(map, chip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameLoop();
    }

    private void gameLoop(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                loadResources();
                if (now - lastEnemyMove > 1_000_000_000L){
                    enemyMovement();
                    lastEnemyMove = now;
                }

                if (game.checkIfComplete()){
                    stop();
                    System.out.println("Level Complete!");
                    return;
                }

                if (!chip.isAlive()){
                    stop();
                    gameCanvas.getScene().setOnKeyPressed(null); // stops user input
                }
            }
        };
        timer.start();
    }

    private void loadResources(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        for (int y = 0; y < map.getHeight(); y++)
            for (int x = 0; x < map.getWidth(); x++){

                Tile tile = map.getTileAt(x, y);
                gc.drawImage(tile.getImage(), x * TILE_SIZE, y * TILE_SIZE);

            }

        gc.drawImage(chip.getImage(), chip.getX() * TILE_SIZE, chip.getY() * TILE_SIZE);

        for (Enemy e : map.getEnemies()) {
            gc.drawImage(e.getImage(), e.getX() * TILE_SIZE, e.getY() * TILE_SIZE);
        }
    }

    public void onKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W -> game.charMove('W');
            case A -> game.charMove('A');
            case S -> game.charMove('S');
            case D -> game.charMove('D');
        }
        loadResources();
    }

    private void enemyMovement(){
            for (Enemy enemy : map.getEnemies()) {
                enemy.move(map, chip);
            }
    }

    public void setGame(Game game) { this.game = game; }
    public void setMap(Map map) { this.map = map; }
    public void setChip(Chip chip) { this.chip = chip; }

}
