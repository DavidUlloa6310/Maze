package com.company;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static com.company.Main.swordPath;
import static com.company.Main.tileSize;

public class Maze {
    private boolean[][] maze;

    private int playerSpawnX, playerSpawnY;

    private int endX, endY;
    private ArrayList<Minotaur> minotaurs;

    private int swordX;
    private int swordY;
    private ImageView sword;

    private String blockedTiles;
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

    public Maze(boolean[][] maze, String walkableTiles, String blockedTiles, Point playerSpawn, Point end, Minotaur minotaur, Point sword) {
        this.maze = maze;
        this.blockedTiles = blockedTiles;
        this.walkableTiles = walkableTiles;

        this.playerSpawnX = playerSpawn.getX();
        this.playerSpawnY = playerSpawn.getY();

        this.endX = end.getX();
        this.endY = end.getY();

        minotaurs = new ArrayList<Minotaur>();
        minotaurs.add(minotaur);

        this.swordX = sword.getX();
        this.swordY = sword.getX();
    }

    public Maze(boolean[][] maze, String walkableTiles, String blockedTiles, Point playerSpawn, Point end, ArrayList<Minotaur> minotaurs, Point sword) {
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

                if (c == swordX && r == swordY) {

                    Image image = new Image(walkableTiles);
                    ImageView iv = new ImageView();
                    iv.setImage(image);
                    iv.relocate(c * tileSize, r * tileSize);
                    groupTiles.getChildren().add(iv);

                    Image swordImg = new Image(swordPath);
                    sword = new ImageView();
                    sword.setImage(swordImg);
                    sword.relocate(c * tileSize, r * tileSize);
                    groupTiles.getChildren().add(sword);
                } else if (c == endX && r == endY) {
                    Rectangle endpoint = new Rectangle(tileSize, tileSize, Color.GOLD);
                    endpoint.relocate(c * tileSize, r * tileSize);
                    groupTiles.getChildren().add(endpoint);
                } else if (maze[r][c]) {
                    Image image = new Image(blockedTiles);
                    ImageView iv = new ImageView();
                    iv.setImage(image);
                    iv.relocate(c * tileSize, r * tileSize);
                    groupTiles.getChildren().add(iv);
                } else if (!maze[r][c]) {
                    Image image = new Image(walkableTiles);
                    ImageView iv = new ImageView();
                    iv.setImage(image);
                    iv.relocate(c * tileSize, r * tileSize);
                    groupTiles.getChildren().add(iv);
                }

                for (Minotaur minotaur : minotaurs) {
                    if (minotaur.getSpawnX() == c && minotaur.getSpawnY() == r) {
                        minotaur.generateModel(root, c, r);
                    }
                }

            }


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
