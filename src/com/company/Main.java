package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    public static final int width = 24;
    public static final int height = 16;
    public static final int tileSize = 25;

    public static final String barrierTilePath = "images/blocks/testBarrier.png";
    public static final String minotaurTilePath = "images/characters/minotaur.png";
    public static final String playerTilePath = "images/characters/player.png";
    public static final String swordPath = "images/items/sword.png";
    public static final String heartPath = "images/items/heart.png";

    public static final String grassTilePath = "images/blocks/grassBlock.png";
    public static final String woodTilePath = "images/blocks/woodPlank.png";

    public static ArrayList<String> caveBlocks;
    public static final String cobbleStonePath = "images/blocks/cobblestone.png";
    public static final String stoneBrickPath = "images/blocks/stoneBrickBlock.png";
    public static final String goldOrePath = "images/blocks/goldOreBlock.png";
    public static final String ironOrePath = "images/blocks/ironOreBlock.png";
    public static final String diamondOrePath = "images/blocks/diamondOreBlock.png";
    public static final String stoneBlockPath = "images/blocks/stoneBlock.png";
    public static final String lavaBlockPath = "images/blocks/lavaBlock.png";
    public static final String redStoneBlockPath = "images/blocks/redstoneBlock.png";
    public static final String lapisBlockPath = "images/blocks/lapisBlock.png";
    public static final String bedrockBlockPath = "images/blocks/bedrockBlock.png";
    public static final String mossyCobbleStonePath = "images/blocks/mossyCobbleStoneBlock.png";

    public static final String netherrackBlockPath = "images/blocks/netherrack.png";
    public static final String netherBrickPath = "images/blocks/netherBrick.png";

    public static final String endstonePath = "images/blocks/endstone.png";
    public static final String obsidianPath = "images/blocks/obsidian.png";

    public static final String musicPath = "src/media/music.mp3";
    public static final String deathSoundPath = "src/media/deathSound.mp3";

    public MediaPlayer mediaPlayer;
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
    public void start(Stage stage) throws FileNotFoundException {
        level = new AtomicInteger();

        canvas = new Canvas(width * tileSize, height * tileSize);
        g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0,0, width * tileSize, height * tileSize);
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        Group userInterface = new Group();

        mazes.add(new Maze(mazeOne, woodTilePath, grassTilePath, new Point(0,0), new Point(23,0), generateMapOneMinotaurs(), new Point(10,10)));
        mazes.add(new Maze(mazeOne, stoneBrickPath, generateCaveBlocks(), new Point(0,0), new Point(23,0), generateMapTwoMinotaurs(), new Point(15,10)));
        mazes.add(new Maze(mazeThree, netherrackBlockPath, netherBrickPath, new Point(0,0), new Point(23,0), generateMapThreeMinotaurs(), new Point(15,10)));
        mazes.add(new Maze(mazeFour, endstonePath, obsidianPath, new Point(0,0), new Point(23,0), generateMapFourMinotaurs(), new Point(15,10)));

        final Maze[] maze = {mazes.get(0)};
        maze[0].generateMaze(root);

        player = new PlayerModel(playerTilePath);
        player.generateModel(root, maze[0].getPlayerSpawnX(),maze[0].getPlayerSpawnY());

        maze[0].bringMinotaursForward();
        updatePlayerUI(userInterface, player);

        animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    for (Minotaur minotaur : maze[0].getMinotaurs()) {

                        if (!minotaur.isDead()) {
                            lastTick = now;
                            minotaur.move(player, maze[0]);
                            if (minotaur.checkDamage(stage, player)) {
                                Media media = new Media(new File(deathSoundPath).toURI().toString());
                                MediaPlayer deathPlayer = new MediaPlayer(media);
                                deathPlayer.setAutoPlay(true);
                            }
                            updatePlayerUI(userInterface, player);
                            return;
                        }
                    }
                }

                if (now - lastTick > 500000000 / (level.intValue() + .75)) {
                    lastTick = now;
                    for (Minotaur minotaur : maze[0].getMinotaurs()) {

                        if (!minotaur.isDead()) {
                            minotaur.move(player, maze[0]);
                            if (minotaur.checkDamage(stage, player)) {
                                Media media = new Media(new File(deathSoundPath).toURI().toString());
                                MediaPlayer deathPlayer = new MediaPlayer(media);
                                deathPlayer.setAutoPlay(true);
                            }
                            updatePlayerUI(userInterface, player);
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
                updatePlayerUI(userInterface, player);
            }

            if ((player.getX() == maze[0].getEndX() && player.getY() == maze[0].getEndY()) || key.getCode() == KeyCode.F) {
                if (level.intValue() != mazes.size() - 1) {
                    level.getAndIncrement();

                    maze[0].clearMaze();
                    maze[0] = mazes.get(level.intValue());
                    maze[0].generateMaze(root);

                    player.respawn(maze[0].getPlayerSpawnX(), maze[0].getPlayerSpawnY());
                    maze[0].respawnMinotaurs(root);

                    animationTimer.start();
                }
            }

        });

        Media musicFile = new Media(new File(musicPath).toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setVolume(.9);
        mediaPlayer.setAutoPlay(true);

        root.getChildren().add(userInterface);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Maze");
        stage.show();

    }

    //MIGHT NEED TO ADD FUNCTION FOR STARTING MAZE, PARAMS FOR PLAYER AND MAZE

    public void updatePlayerUI(Group userInterface, PlayerModel player) {

        userInterface.getChildren().clear();

        for (int i = 0; i < player.getHealth(); i++) {
            Image image = new Image(heartPath);
            ImageView imageView = new ImageView(image);
            imageView.relocate(24 * tileSize,i * tileSize);
            userInterface.getChildren().add(imageView);
        }

        if (player.hasSword()) {
            Image image = new Image(swordPath);
            ImageView imageView = new ImageView(image);
            imageView.relocate(24 * tileSize, 4 * tileSize);
            userInterface.getChildren().add(imageView);
        }

    }

    public ArrayList<Minotaur> generateMapOneMinotaurs() {
        ArrayList<Minotaur> minotaurs = new ArrayList<Minotaur>();

        minotaurs.add(new Minotaur(new Point(10,10)));
        //minotaurs.add(new Minotaur(new Point(10,11)));

        return minotaurs;
    }

    public ArrayList<Minotaur> generateMapTwoMinotaurs() {
        ArrayList<Minotaur> minotaurs = new ArrayList<Minotaur>();
        minotaurs.add(new Minotaur(new Point(10,10), minotaurTilePath));
        minotaurs.add(new Minotaur(new Point(5,10), minotaurTilePath));
        //minotaurs.add(new Minotaur(new Point(20,10), minotaurTilePath));
        return minotaurs;
    }

    public ArrayList<Minotaur> generateMapThreeMinotaurs() {
        ArrayList<Minotaur> minotaurs = new ArrayList<Minotaur>();
        minotaurs.add(new Minotaur(new Point(10,10), minotaurTilePath));
        minotaurs.add(new Minotaur(new Point(5,10), minotaurTilePath));
        minotaurs.add(new Minotaur(new Point(20,10), minotaurTilePath));
        return minotaurs;
    }

    public ArrayList<Minotaur> generateMapFourMinotaurs() {
        ArrayList<Minotaur> minotaurs = new ArrayList<Minotaur>();
        return minotaurs;
    }

    public ArrayList<String> generateCaveBlocks() {
        ArrayList<String> caveBlocks = new ArrayList<>();

        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(lavaBlockPath);
        caveBlocks.add(diamondOrePath);
        caveBlocks.add(ironOrePath);
        caveBlocks.add(goldOrePath);


        return caveBlocks;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
