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
    public AtomicInteger level;

    AnimationTimer animationTimer;

    Canvas canvas;
    GraphicsContext g;
    PlayerModel player;
    Minotaur minotaur;

    ArrayList<Maze> mazes = new ArrayList<>();
    /* Tele-porter?, Add sword, Add minotaur */

    boolean[][] mazeOne = {
            {false, false, false, false, true , true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , true , true , false, true , true , true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, true , false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true },
            {false, true , false, true , true , true , false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true },
            {false, true , false, false, false, true , false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {true , true , true , true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, true , false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , true , true , false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true , true , true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {true , true , true , true , false, false, false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true , false, true , false, false, false, false, false, false, false, true , true , true , true , false, false, false, false, false},
            {false, true , true , true , true , true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false ,false, false, false, false, false, true , false, false, false, false, false, false, false, true , false , true , true , false, true , true , true , true },
            {false, true , true , true , true , true , true , true , false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false},
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

    @Override
    public void start(Stage stage) {
        level = new AtomicInteger();

        canvas = new Canvas(width * tileSize, height * tileSize);
        g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0,0, width * tileSize, height * tileSize);
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        mazes.add(new Maze(mazeOne, new Point(0,0), new Point(23,0), new Point(10,10), new Point(10,10)));
        mazes.add(new Maze(mazeTwo, new Point(0,0), new Point(23,0), new Point(10,10), new Point(15,10)));
        mazes.add(new Maze(mazeThree, new Point(0,0), new Point(23,0), new Point(10,10), new Point(15,10)));

        final Maze[] maze = {mazes.get(0)};
        maze[0].generateMaze(root);

        player = new PlayerModel(Color.BLUE);
        player.generateModel(root, maze[0].getPlayerSpawnX(),maze[0].getPlayerSpawnY());

        minotaur = new Minotaur();
        minotaur.generateModel(root, maze[0].getMinotaurX(), maze[0].getMinotaurY());

        animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    minotaur.move(player, maze[0]);
                    return;
                }

                if (now - lastTick > 500000000 / (level.intValue() + 1)) {
                    lastTick = now;
                    minotaur.move(player, maze[0]);

                    if (minotaur.getX() == player.getX() && minotaur.getY() == player.getY()) {
                        if (player.hasSword()) {
                            player.removeSword();
                            stop();
                        } else {
                            stage.close();
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
                    minotaur.respawn(maze[0].getMinotaurX(), maze[0].getMinotaurY());

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
