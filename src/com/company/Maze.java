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

    private ArrayList<Point> minotaurSpawns;
    private ArrayList<Minotaur> minotaurs;

    private int swordX;
    private int swordY;
    private ImageView sword;

    private String blockedTiles;
    private String walkableTiles;

    private Group groupRectangles;

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

    public Maze(boolean[][] maze, String walkableTiles, String blockedTiles, Point playerSpawn, Point end, Point minotaurSpawn, Minotaur minotaur, Point sword) {
        this.maze = maze;
        this.blockedTiles = blockedTiles;
        this.walkableTiles = walkableTiles;

        this.playerSpawnX = playerSpawn.getX();
        this.playerSpawnY = playerSpawn.getY();

        this.endX = end.getX();
        this.endY = end.getY();

        minotaurSpawns = new ArrayList<Point>();
        minotaurSpawns.add(minotaurSpawn);

        minotaurs = new ArrayList<Minotaur>();
        minotaurs.add(minotaur);

        this.swordX = sword.getX();
        this.swordY = sword.getX();
    }

    public Maze(boolean[][] maze, String walkableTiles, String blockedTiles, Point playerSpawn, Point end, ArrayList<Point> minotaurSpawns, ArrayList<Minotaur> minotaurs, Point sword) {
        this.maze = maze;
        this.blockedTiles = blockedTiles;
        this.walkableTiles = walkableTiles;

        this.playerSpawnX = playerSpawn.getX();
        this.playerSpawnY = playerSpawn.getY();

        this.minotaurSpawns = minotaurSpawns;
        this.minotaurs = minotaurs;

        this.endX = end.getX();
        this.endY = end.getY();

        this.swordX = sword.getX();
        this.swordY = sword.getX();
    }


    public void generateMaze(Pane root) {

        groupRectangles = new Group();

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {

                if (c == swordX && r == swordY) {

                    Image image = new Image(walkableTiles);
                    ImageView iv = new ImageView();
                    iv.setImage(image);
                    iv.relocate(c * tileSize, r * tileSize);
                    groupRectangles.getChildren().add(iv);

                    Image swordImg = new Image(swordPath);
                    sword = new ImageView();
                    sword.setImage(swordImg);
                    sword.relocate(c * tileSize, r * tileSize);
                    groupRectangles.getChildren().add(sword);
                } else if (c == endX && r == endY) {
                    Rectangle endpoint = new Rectangle(tileSize, tileSize, Color.GOLD);
                    endpoint.relocate(c * tileSize, r * tileSize);
                    groupRectangles.getChildren().add(endpoint);
                } else if (maze[r][c]) {
                    Image image = new Image(blockedTiles);
                    ImageView iv = new ImageView();
                    iv.setImage(image);
                    iv.relocate(c * tileSize, r * tileSize);
                    groupRectangles.getChildren().add(iv);
                } else if (!maze[r][c]) {
                    Image image = new Image(walkableTiles);
                    ImageView iv = new ImageView();
                    iv.setImage(image);
                    iv.relocate(c * tileSize, r * tileSize);
                    groupRectangles.getChildren().add(iv);
                }

                for (int i = 0; i < minotaurSpawns.size(); i++) {
                    if (minotaurSpawns.get(i).getX() == c && minotaurSpawns.get(i).getY() == r && !groupRectangles.getChildren().contains(minotaurs.get(i))) {
                        minotaurs.get(i).generateModel(root, c, r);
                    }
                }

            }


        }

        root.getChildren().add(groupRectangles);
    }

    public void clearMaze() {
        groupRectangles.getChildren().clear();
    }

    public void clearSword() {
        groupRectangles.getChildren().remove(sword);
    }

    public void respawnMinotaurs(Pane root) {
        for (int i = 0; i < minotaurSpawns.size(); i++) {
            Point p = minotaurSpawns.get(i);
            Minotaur minotaur = minotaurs.get(i);
            minotaur.respawn(p.getX(), p.getY());
        }
    }

    public void bringMinotaursForward() {
        for (Minotaur m : minotaurs) {
            m.getImage().toFront();
        }
    }

}
