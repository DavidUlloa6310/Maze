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
    private ArrayList<Mob> mobs;

    private ArrayList<Object> swords;
    private ImageView swordView;

    private ArrayList<String> blockedTiles;
    private String walkableTiles;

    private Group groupTiles;

    public boolean[][] getMaze() { return maze; }

    public int getPlayerSpawnX() { return playerSpawnX; }
    public int getPlayerSpawnY() { return playerSpawnY; }

    public int getEndX() { return endX; }
    public int getEndY() { return endY; }

    public ArrayList<Mob> getMobs() { return mobs; }

    public ArrayList<Object> getObjects() {return this.swords; }

    public void setMaze(boolean[][] maze) {
        this.maze = maze;
    }

    public Maze(boolean[][] maze, String walkableTiles, String blockedTiles, Point playerSpawn, Point end, ArrayList<Mob> mobs, Object sword) {
        this.maze = maze;

        this.blockedTiles = new ArrayList<>();
        this.blockedTiles.add(blockedTiles);

        this.walkableTiles = walkableTiles;

        this.playerSpawnX = playerSpawn.getX();
        this.playerSpawnY = playerSpawn.getY();

        this.mobs = mobs;

        this.endX = end.getX();
        this.endY = end.getY();

        swords = new ArrayList<Object>();
        swords.add(sword);
    }

    public Maze(boolean[][] maze, String walkableTiles, ArrayList<String> blockedTiles, Point playerSpawn, Point end, ArrayList<Mob> mobs, ArrayList<Object> swords) {
        this.maze = maze;
        this.blockedTiles = blockedTiles;
        this.walkableTiles = walkableTiles;

        this.playerSpawnX = playerSpawn.getX();
        this.playerSpawnY = playerSpawn.getY();

        this.mobs = mobs;

        this.endX = end.getX();
        this.endY = end.getY();

        this.swords = swords;
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

        for (Object sword : swords) {
            Image swordImg = new Image(swordPath);
            swordView = new ImageView();
            swordView.setImage(swordImg);
            swordView.relocate(sword.getPoint().getX() * tileSize, sword.getPoint().getY() * tileSize);
            sword.setImageView(swordView);
            groupTiles.getChildren().add(swordView);
        }

        Rectangle endpoint = new Rectangle(tileSize, tileSize, Color.GOLD);
        endpoint.relocate(endX* tileSize, endY * tileSize);
        groupTiles.getChildren().add(endpoint);

        for (Mob mob : mobs) {
            mob.generateModel(root);
        }

        root.getChildren().add(groupTiles);
    }

    public void clearMaze() {
        groupTiles.getChildren().clear();
    }

    public void clearSword(Object sword) {
        groupTiles.getChildren().remove(sword.getImageView());
        sword.setGone(true);
    }

    public void respawnMobs(Pane root) {
        for (Mob mob : mobs) {
            mob.respawn(mob.getSpawnX(), mob.getSpawnY());
        }
    }

    public void bringMobsForward() {
        for (Mob m : mobs) {
            m.getImage().toFront();
        }
    }

}
