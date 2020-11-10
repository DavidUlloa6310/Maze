package com.company;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import static com.company.Main.swordPath;
import static com.company.Main.tileSize;

public class Maze {
    private boolean[][] maze;
    Random random;

    private int playerSpawnX, playerSpawnY;

    private int endX, endY;
    private ArrayList<Minotaur> minotaurs;

    private int swordX;
    private int swordY;
    private ImageView sword;

    private ArrayList<String> blockedTiles;
    private String walkableTiles;

    private Group groupTiles;

    public boolean[][] getMaze() { return maze; }

    public int getPlayerSpawnX() { return playerSpawnX; }
    public int getPlayerSpawnY() { return playerSpawnY; }

    public int getEndX() { return endX; }
    public int getEndY() { return endY; }

    public ArrayList<Minotaur> getMinotaurs() { return minotaurs; }

    public int getSwordX() { return swordX; }
    public int getSwordY() { return swordY; }

    public void setMaze(boolean[][] maze) {
        this.maze = maze;
    }

    public Maze(boolean[][] maze, String walkableTiles, String blockedTiles, Point playerSpawn, Point end, ArrayList<Minotaur> minotaurs, Point sword) {
        this.maze = maze;

        this.blockedTiles = new ArrayList<>();
        this.blockedTiles.add(blockedTiles);

        this.walkableTiles = walkableTiles;

        this.playerSpawnX = playerSpawn.getX();
        this.playerSpawnY = playerSpawn.getY();

        this.minotaurs = minotaurs;

        this.endX = end.getX();
        this.endY = end.getY();

        this.swordX = sword.getX();
        this.swordY = sword.getX();
    }

    public Maze(boolean[][] maze, String walkableTiles, ArrayList<String> blockedTiles, Point playerSpawn, Point end, ArrayList<Minotaur> minotaurs, Point sword) {
        this.maze = maze;
        this.blockedTiles = blockedTiles;
        this.walkableTiles = walkableTiles;

        this.playerSpawnX = playerSpawn.getX();
        this.playerSpawnY = playerSpawn.getY();

        this.minotaurs = minotaurs;

        this.endX = end.getX();
        this.endY = end.getY();

        this.swordX = sword.getX();
        this.swordY = sword.getX();
    }


    public void generateMaze(Pane root) {

        groupTiles = new Group();

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {

                Image image = new Image(walkableTiles);
                ImageView iv = new ImageView();
                iv.setImage(image);
                iv.relocate(c * tileSize, r * tileSize);
                groupTiles.getChildren().add(iv);

                if (maze[r][c]) {

                    random = new Random();
                    int index = random.nextInt(blockedTiles.size());

                    Image blockedImage = new Image(blockedTiles.get(index));
                    ImageView blockedIv = new ImageView();
                    blockedIv.setImage(blockedImage);
                    blockedIv.relocate(c * tileSize, r * tileSize);
                    groupTiles.getChildren().add(blockedIv);
                }

            }


        }

        Image swordImg = new Image(swordPath);
        sword = new ImageView();
        sword.setImage(swordImg);
        sword.relocate(swordX* tileSize, swordY* tileSize);
        groupTiles.getChildren().add(sword);

        Rectangle endpoint = new Rectangle(tileSize, tileSize, Color.GOLD);
        endpoint.relocate(endX* tileSize, endY * tileSize);
        groupTiles.getChildren().add(endpoint);

        for (Minotaur minotaur : minotaurs) {
            minotaur.generateModel(root);
        }

        root.getChildren().add(groupTiles);
    }

    public void clearMaze() {
        groupTiles.getChildren().clear();
    }

    public void clearSword() {
        groupTiles.getChildren().remove(sword);
    }

    public void respawnMinotaurs(Pane root) {
        for (Minotaur minotaur : minotaurs) {
            minotaur.respawn(minotaur.getSpawnX(), minotaur.getSpawnY());
        }
    }

    public void bringMinotaursForward() {
        for (Minotaur m : minotaurs) {
            m.getImage().toFront();
        }
    }

}
