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
    public static final String creeperTilePath = "images/characters/creeper.png";
    public static final String blazeTilePath = "images/characters/blaze.png";
    public static final String endermanTilePath = "images/characters/enderman.png";
    public static final String skeletonTilePath = "images/characters/skeleton.png";
    public static final String spiderTilePath = "images/characters/spider.png";
    public static final String ricoTilePath = "images/characters/rico.png";

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
    public static final String teleporterBlockPath = "images/blocks/portalBlock.png";

    public static final String musicPath = "src/media/music.mp3";
    public static final String deathSoundPath = "src/media/deathSound.mp3";

    public MediaPlayer songMediaPlayer = new MediaPlayer(new Media(new File(musicPath).toURI().toString()));

    public AtomicInteger level;

    AnimationTimer animationTimer;

    Canvas canvas;
    GraphicsContext g;
    PlayerModel player;

    ArrayList<Maze> mazes = new ArrayList<>();

    ArrayList<String> playerInputs = new ArrayList<>();
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
            {false, true , false, false, false, false, false, true , false, false, true , false, true , true , true , false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, true , false, false, true , false, false, false, true , false, false, true , false, false, false, false, true , false},
            {false, true , false, true , true , true , false, true , false, false, true , false, true , false, true , false, false, true , false, true , true , true , true , false},
            {false, false, false, true , false, false, false, true , false, false, false, false, true , false, true , false, false, true , false, true , false, false, false, false},
            {false, true , true , true , true , false, false, true , false, true , true , true , true , false, false, false, false, false, false, true , false, true , false, true },
            {false, false, false, false, true , true , false, true , true , false, false, false, true , true , true , false, false, true , false, true , false, true , false, true },
            {false, true , true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, true , false, true , false, true , false, true },
            {true , true , true , true , true , false, false, true , true , true , true , true , false, false, true , false, false, true , false, false, false, false, false, false},
            {true , true , false, false, false, false, false, true , true , false, false, true , true , false, true , false, true , true , true , true , true , true , true , false},
            {false, true , false, true , true , true , false, true , true , false, false, true , false, false, true , false, true , false, false, false, false, false, true , false},
            {false, true , false, false, false, false, false, false, true , false, false, true , false, false, true , false, true , false, true , false, false, false, true , false},
            {false, true , false, true , true , false, false, false, false, false, false, false, false, false, true , false, false, false, true , false, false, false, true , false},
            {false, true , false, true , true , true , true , true , true , true , true , false, false, false, false, false, true , false, true , false, false, false, true , false},
            {false, false, false, false, false, false, false, false, false, false, true , false, false, true , false, true , true , false, true , false, false, false, true , false},
            {true , true , true , true , true , false, false, false, true , false, true , false, false, true , false, false, true , false, true , false, false, false, false, false},
            {true , false, false, false, false, false, false, true , true , false, false, false, false, true , false, false, false, false, true , false, false, true , true , false},
    };

    boolean[][] mazeThree = {
            {false, false, true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false, true },
            {false, true , false, false, true , false, true , false, false, true , false, true , false, true , true , true , true , true , false, false, false, false, false, false},
            {false, true , false, true , true , false, true , false, false, true , false, false, false, false, false, false, false, true , false, true , true , false, true , false},
            {false, false, false, false, true , false, true , false, false, true , false, false, false, true , true , false, false, true , false, true , false, false, true , false},
            {false, true , true , false, true , false, true , false, false, true , false, false, false, false, true , false, false, true , false, true , false, false, true , false},
            {false, false, true , false, true , false, true , false, false, true , false, false, false, false, true , false, false, true , false, true , false, false, true , false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {true , true , true , false, true , true , false, false, false, false, false, false, false, false, false, false, false, false, false, true , true , true , true , false},
            {false, false, false, false, false, false, false, false, false, true , false, false, false, false, true , false, false, true , false, false, false, false, false, false},
            {false, false, true , false, true , false, true , false, false, true , false, false, false, false, true , false, false, true , false, true , true , true , true , false},
            {false, false, true , false, true , false, true , false, false, true , true , false, false, true , true , false, false, true , false, false, false, false, false, false},
            {false, false, true , false, true , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true , false},
            {false, false, false, false, true , false, true , false, true , true , false, false, false, true , true , true , true , true , true , true , true , true , true , false},
            {false, false, true , false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, true , true , true , true , true , true , true , true , true , true , false, true , true , true , true , true , true , true , true , true , true , true },
    };

    boolean[][] mazeFour = {
            {false, true , false, true , false, false, false, true , false, false, false, false, false, false, true , false, true , true , true , false, false, false, false, false},
            {false, true , false, true , false, true , false, true , false, true , true , true , true , true , true , false, false, false, false, false, true , true , true , true },
            {false, true , false, true , false, true , false, true , false, true , false, false, false, false, true , true , true , true , true , false, true , false, false, false},
            {false, true , false, true , true , true , false, true , false, true , false, true , false, false, false, false, false, false, false, false, true , false, false, false},
            {false, false, false, true , false, true , false, true , false, true , false, true , true , true , true , true , true , true , true , true , true , true , true , false},
            {false, true , false, false, false, true , false, false, false, true , false, false, false, false, false, false, false, false, true , false, true , false, true , false},
            {false, true , true , true , false, true , false, true , true , true , false, true , true , false, true , false, true , true , true , false, true , false, true , false},
            {false, true , false, false, false, true , false, false, false, false, false, false, true , false, true , false, true , false, false, false, true , false, false, false},
            {false, false, false, true , true , true , false, true , true , true , true , false, true , false, true , false, true , false, true , true , true , false, true , false},
            {true , true , false, true , false, false, false, false, false, false, true , false, true , false, true , false, true , false, true , false, false, false, true , false},
            {false, false, false, true , false, true , true , true , true , false, true , false, true , true , true , false, true , false, true , false, true , false, true , false},
            {false, true , true , true , false, true , false, false, false, false, false, false, true , false, true , false, true , false, true , true , true , false, true , true },
            {false, true , false, true , false, true , false, true , false, true , true , true , true , false, true , false, true , false, true , false, true , true , true , false},
            {false, true , false, false, false, true , false, true , false, false, false, false, false, false, false, false, true , false, false, false, false, false, false, false},
            {false, true , true , true , true , true , false, true , true , true , true , false, true , false, true , true , true , true , true , false, true , true , true , true },
            {false, false, false, false, false, false, false, true , false, false, false, false, true , false, false, false, true , false, false, false, true , false, false, false},
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

        mazes.add(new Maze(mazeOne, woodTilePath, grassTilePath, new Point(0,0), new Point(23,0), generateMapOneMinotaurs(), generateMapOneSwords()));
        mazes.add(new Maze(mazeTwo, stoneBrickPath, generateCaveBlocks(), new Point(0,0), new Point(21,13), generateMapTwoMinotaurs(), generateMapTwoSwords()));
        mazes.add(new Maze(mazeThree, netherrackBlockPath, netherBrickPath, new Point(11,7), new Point(22,0), generateMapThreeMinotaurs(), generateMapThreeSwords()));
        mazes.add(new Maze(mazeFour, endstonePath, obsidianPath, new Point(0,0), new Point(21,3), generateMapFourMinotaurs(), generateMapFourSwords(), generateMapFourTeleporters()));

        final Maze[] maze = {mazes.get(0)};
        maze[0].generateMaze(root);

        player = new PlayerModel();
        player.generateModel(root, maze[0].getPlayerSpawnX(),maze[0].getPlayerSpawnY());

        maze[0].bringMobsForward();
        updatePlayerUI(userInterface, player);

        animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    for (Mob mob : maze[0].getMobs()) {

                        if (!mob.isDead()) {
                            lastTick = now;
                            mob.move(player, maze[0]);
                            if (mob.checkDamage(stage, player)) {
                                Media media = new Media(new File(deathSoundPath).toURI().toString());
                                MediaPlayer deathPlayer = new MediaPlayer(media);
                                deathPlayer.setAutoPlay(true);
                            }
                            updatePlayerUI(userInterface, player);
                            //return; maybe don;t need?
                        }
                    }
                }

                if (now - lastTick > 500000000 / (level.intValue() + .75)) {
                    lastTick = now;
                    for (Mob mob : maze[0].getMobs()) {

                        if (!mob.isDead()) {
                            mob.move(player, maze[0]);
                            if (mob.checkDamage(stage, player)) {
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
                playerInputs.add("W");
            } else if (key.getCode() == KeyCode.A) {
                player.moveLeft(maze[0]);
                playerInputs.add("A");
            } else if (key.getCode() == KeyCode.S) {
                player.moveDown(maze[0]);
                playerInputs.add("S");
            } else if (key.getCode() == KeyCode.D) {
                player.moveRight(maze[0]);
                playerInputs.add("D");
            } else if (key.getCode() == KeyCode.B) {
                playerInputs.add("B");
            } else if (key.getCode() == KeyCode.ENTER) {
                playerInputs.add("ENTER");
            } else {
                playerInputs.clear();
            }

            if (playerInputs.equals(konamiCode())) {
                player.setImage(root, new Image(ricoTilePath));
            }

            if ((player.getX() == maze[0].getEndX() && player.getY() == maze[0].getEndY()) || key.getCode() == KeyCode.L) {
                if (level.intValue() != mazes.size() - 1) {
                    level.getAndIncrement();

                    maze[0].clearMaze();
                    maze[0] = mazes.get(level.intValue());
                    maze[0].generateMaze(root);

                    player.respawn(maze[0].getPlayerSpawnX(), maze[0].getPlayerSpawnY());
                    maze[0].respawnMobs(root);

                    animationTimer.start();
                } else {
                    stage.close();
                    //END GAME
                }
            }

            for (Mob mob : maze[0].getMobs()) {

                if (!mob.isDead() && mob.checkDamage(stage, player)) {
                    Media media = new Media(new File(deathSoundPath).toURI().toString());
                    MediaPlayer deathPlayer = new MediaPlayer(media);
                    deathPlayer.setAutoPlay(true);
                }
                updatePlayerUI(userInterface, player);
            }

            for (Object sword : maze[0].getObjects()) {

                if (player.getX() == sword.getPoint().getX() && player.getY() == sword.getPoint().getY() && !sword.isGone()) {
                    player.addSword();
                    maze[0].clearSword(sword);
                    updatePlayerUI(userInterface, player);
                }
            }

            for (Teleporter teleporter : maze[0].getTeleporters()) {
                if (player.getX() == teleporter.getStartPoint().getX() && player.getY() == teleporter.getStartPoint().getY()) {
                    player.respawn(teleporter.getTeleportPoint().getX(), teleporter.getTeleportPoint().getY());
                    break;
                }
            }

        });

        songMediaPlayer.setAutoPlay(true);

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

    public ArrayList<Mob> generateMapOneMinotaurs() {
        ArrayList<Mob> mobs = new ArrayList<Mob>();

        mobs.add(new Mob(new Point(10,10)));
        //minotaurs.add(new Minotaur(new Point(10,11)));

        return mobs;
    }
    public ArrayList<Object> generateMapOneSwords() {
        ArrayList<Object> swords = new ArrayList<Object>();
        swords.add(new Object(new Point(10,10)));
        return swords;
    }

    public ArrayList<Mob> generateMapTwoMinotaurs() {
        ArrayList<Mob> mobs = new ArrayList<Mob>();
        mobs.add(new Mob(new Point(10,10), spiderTilePath));
        mobs.add(new Mob(new Point(5,10), spiderTilePath));
        mobs.add(new Mob(new Point(20,10), spiderTilePath));
        return mobs;
    }
    public ArrayList<Object> generateMapTwoSwords() {
        ArrayList<Object> swords = new ArrayList<Object>();
        swords.add(new Object(new Point(17, 10)));
        swords.add(new Object(new Point(5, 10)));
        swords.add(new Object(new Point(8, 13)));
        return swords;
    }

    public ArrayList<Mob> generateMapThreeMinotaurs() {
        ArrayList<Mob> mobs = new ArrayList<Mob>();
        mobs.add(new Mob(new Point(5,2), blazeTilePath));
        mobs.add(new Mob(new Point(21,3), blazeTilePath));
        mobs.add(new Mob(new Point(17,11), blazeTilePath));
        mobs.add(new Mob(new Point(5,13), blazeTilePath));
        return mobs;
    }
    public ArrayList<Object> generateMapThreeSwords() {
        ArrayList<Object> swords = new ArrayList<Object>();
        swords.add(new Object(new Point(7,7)));
        swords.add(new Object(new Point(19,7)));
        swords.add(new Object(new Point(20,11)));
        swords.add(new Object(new Point(5,14)));
        return swords;

    }

    public ArrayList<Mob> generateMapFourMinotaurs() {
        ArrayList<Mob> mobs = new ArrayList<Mob>();
        mobs.add(new Mob(new Point(6,3), endermanTilePath));
        mobs.add(new Mob(new Point(13,2), endermanTilePath));
        mobs.add(new Mob(new Point(17,14), endermanTilePath));
        return mobs;
    }
    public ArrayList<Object> generateMapFourSwords() {
        ArrayList<Object> swords = new ArrayList<Object>();
        swords.add(new Object(new Point(0,7)));
        swords.add(new Object(new Point(1,10)));
        swords.add(new Object(new Point(16,1)));
        return swords;
    }
    public ArrayList<Teleporter> generateMapFourTeleporters() {
        ArrayList<Teleporter> teleporters = new ArrayList<Teleporter>();
        teleporters.add(new Teleporter(new Point(2,12), new Point(15,0)));
        teleporters.add(new Teleporter(new Point(15,0), new Point(19,15)));
        teleporters.add(new Teleporter(new Point(19,15), new Point(19, 5)));
        teleporters.add(new Teleporter(new Point(19,5), new Point(23,10)));
        teleporters.add(new Teleporter(new Point (11, 7), new Point(19,15)));
        return teleporters;
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

    public ArrayList<String> konamiCode() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("W");
        arrayList.add("W");
        arrayList.add("S");
        arrayList.add("S");
        arrayList.add("A");
        arrayList.add("D");
        arrayList.add("A");
        arrayList.add("D");
        arrayList.add("B");
        arrayList.add("A");
        arrayList.add("ENTER");
        return arrayList;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
