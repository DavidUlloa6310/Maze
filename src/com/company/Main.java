package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    public static final int width = 24;
    public static final int height = 16;
    public static final int tileSize = 25;

    public static final String barrierTilePath = "testBarrier.png";
    public static final String minotaurTilePath = "minotaur.png";
    public static final String playerTilePath = "player.png";
    public static final String swordPath = "sword.png";

    public static final String grassTilePath = "grassBlock.png";
    public static final String woodTilePath = "woodPlank.png";

    public static final String cobbleStonePath = "cobblestone.png";
    public static final String stoneBlockPath = "stoneBlock.png";

    public static final String netherrackBlockPath = "netherrack.png";
    public static final String netherBrickPath = "netherBrick.png";

    public static final String endstonePath = "endstone.png";
    public static final String obsidianPath = "obsidian.png";

    public AtomicInteger level;

    AnimationTimer animationTimer;

    Canvas canvas;
    GraphicsContext g;
    PlayerModel player;

    ArrayList<Maze> mazes = new ArrayList<>();
    /* Tele-porter?, Finish mazes, add sprites */

    boolean[][] mazeOne = {
            {false, false, false, false, true , true , false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, true , false, true , true , true , true , false, true , false, true , true , true , true , false, true , true , false},
            {false, true , true , true , false, true , true , true , false, true , false, false, false, false, true , false, false, false, false, false, false, false, false, false},
            {false, false, false, true , false, true , false, false, false, true , false, true , false, false, false, false, true , true , true , false, true , false, false, false},
            {false, true , false, false, false, false, false, true , false, true , false, true , true , true , true , false, false, false, true , false, true , false, true , true },
            {false, true , false, true , true , true , false, true , false, false, false, false, false, false, true , false, true , false, true , false, false, false, false, true },
            {false, true , false, false, false, false, false, true , true , false, true , true , true , false, true , false, true , false, true , false, true , true , false, false},
            {true , true , true , true , false, true , false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false},
            {false, true , false, false, false, true , false, true , true , true , false, true , true , false, true , true , true , false, true , true , true , true , true , false},
            {false, true , true , true , false, true , false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, true , false},
            {false, false, false, false, false, true , true , true , false, true , false, true , false, true , false, true , true , true , true , true , true , false, true , false},
            {true , true , true , true , false, false, false, true , false, true , false, true , false, true , false, false, false, false, false, false, false, false, true , false},
            {false, false, false, false, false, true , false, true , false, false, false, true , false, true , true , false, true , true , true , false, true , false, true , false},
            {false, true , true , true , true , true , false, false, false, true , false, true , false, false, false, false, false, false, false, false, true , false, false, false},
            {false, false ,false, false, false, false, false, true , false, true , false, true , false, true , false, true , false, true , true , false, true , true , true , true },
            {false, true , true , true , true , true , true , true , false, false, false, false, false, true , false, true , false, false, false, false, false, false, false, false},
    };

    boolean[][] mazeTwo = {
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
    };

    boolean[][] mazeThree = {
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
    };

    boolean[][] mazeFour = {
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
    };

    @Override
    public void start(Stage stage) {
        level = new AtomicInteger();

        canvas = new Canvas(width * tileSize, height * tileSize);
        g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0,0, width * tileSize, height * tileSize);
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);


        ArrayList<Point> mapOneSpawns = new ArrayList<>();
        ArrayList<Minotaur> mapOneMinotaurs = new ArrayList<>();

        mapOneSpawns.add(new Point(10,10));
        mapOneSpawns.add(new Point(10,11));

        mapOneMinotaurs.add(new Minotaur());
        mapOneMinotaurs.add(new Minotaur());

        Minotaur minotaur2 = new Minotaur();
        Minotaur minotaur3 = new Minotaur();
        Minotaur minotaur4 = new Minotaur();

        mazes.add(new Maze(mazeOne, woodTilePath, grassTilePath, new Point(0,0), new Point(23,0), mapOneSpawns, mapOneMinotaurs, new Point(10,10)));
        mazes.add(new Maze(mazeTwo, cobbleStonePath, stoneBlockPath, new Point(0,0), new Point(23,0), new Point(10,10), minotaur2, new Point(15,10)));
        mazes.add(new Maze(mazeThree, netherrackBlockPath, netherBrickPath, new Point(0,0), new Point(23,0), new Point(10,10), minotaur3, new Point(15,10)));
        mazes.add(new Maze(mazeFour, endstonePath, obsidianPath, new Point(0,0), new Point(23,0), new Point(10,10), minotaur4, new Point(15,10)));

        final Maze[] maze = {mazes.get(0)};
        maze[0].generateMaze(root);

        player = new PlayerModel(playerTilePath);
        player.generateModel(root, maze[0].getPlayerSpawnX(),maze[0].getPlayerSpawnY());

        maze[0].bringMinotaursForward();

        animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    for (Minotaur minotaur : maze[0].getMinotaurs()) {

                        if (!minotaur.isDead()) {
                            lastTick = now;
                            minotaur.move(player, maze[0]);
                            return;
                        }
                    }
                }

                if (now - lastTick > 500000000 / (level.intValue() + 1)) {
                    lastTick = now;
                    for (Minotaur minotaur : maze[0].getMinotaurs()) {

                        if (!minotaur.isDead()) {
                            minotaur.move(player, maze[0]);

                            if (minotaur.getX() == player.getX() && minotaur.getY() == player.getY()) {
                                if (player.hasSword()) {
                                    player.removeSword();
                                    minotaur.die();
                                } else {
                                    stage.close();
                                }
                            }
                        }

                    }

                }

            }
        };

        animationTimer.start();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {

            if (key.getCode() == KeyCode.W) {
                player.moveUp(maze[0]);
            }

            if (key.getCode() == KeyCode.A) {
                player.moveLeft(maze[0]);
            }

            if (key.getCode() == KeyCode.S) {
                player.moveDown(maze[0]);
            }

            if (key.getCode() == KeyCode.D) {
                player.moveRight(maze[0]);
            }

            if (player.getX() == maze[0].getSwordX() && player.getY() == maze[0].getSwordY()) {
                player.addSword();
                maze[0].clearSword();
            }

            if (player.getX() == maze[0].getEndX() && player.getY() == maze[0].getEndY()) {
                if (level.intValue() != mazes.size() - 1) {
                    level.getAndIncrement();

                    maze[0].clearMaze();
                    maze[0] = mazes.get(level.intValue());
                    maze[0].generateMaze(root);

                    player.respawn(maze[0].getPlayerSpawnX(), maze[0].getPlayerSpawnY());
                    maze[0].respawnMinotaurs(root);

                    animationTimer.start();

                    System.out.println("you did it");
                }
            }

        });

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Maze");
        stage.show();

    }

    //MIGHT NEED TO ADD FUNCTION FOR STARTING MAZE, PARAMS FOR PLAYER AND MAZE

    public static void main(String[] args) {
        launch(args);
    }
}
