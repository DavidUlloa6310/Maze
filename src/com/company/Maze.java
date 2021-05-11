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
import static com.company.Main.teleporterBlockPath;

public class Maze {
    /*
    Represents the maze with a 2d boolean array.
     */
    private final boolean[][] maze;
    Random random;

    private final int playerSpawnX, playerSpawnY;

    private final int endX, endY;
    private final ArrayList<Mob> mobs;

    private final ArrayList<Object> swords;

    private final ArrayList<String> blockedTiles;
    private final String walkableTiles;

    private ArrayList<Teleporter> teleporters;

    private Group groupTiles;

    public boolean[][] getMaze() { return maze; }

    public int getPlayerSpawnX() { return playerSpawnX; }
    public int getPlayerSpawnY() { return playerSpawnY; }

    public int getEndX() { return endX; }
    public int getEndY() { return endY; }

    public ArrayList<Mob> getMobs() { return mobs; }
    public ArrayList<Object> getObjects() { return this.swords; }
    public ArrayList<Teleporter> getTeleporters() { return this.teleporters; }

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
        this.teleporters = new ArrayList<>();
    }

    public Maze(boolean[][] maze, String walkableTiles, String blockedTile, Point playerSpawn, Point end, ArrayList<Mob> mobs, ArrayList<Object> swords) {
        this(maze, walkableTiles, blockedTileToArrayList(blockedTile), playerSpawn, end, mobs, swords);
        this.teleporters = new ArrayList<>();
    }

    public Maze(boolean[][] maze, String walkableTiles, String blockedTile, Point playerSpawn, Point end, ArrayList<Mob> mobs, ArrayList<Object> swords, ArrayList<Teleporter> teleporters) {
        this(maze, walkableTiles, blockedTileToArrayList(blockedTile), playerSpawn, end, mobs, swords);
        this.teleporters = teleporters;
    }

    public static ArrayList<String> blockedTileToArrayList(String blockedTile) {
        ArrayList<String> blockedTiles = new ArrayList<>();
        blockedTiles.add(blockedTile);
        return blockedTiles;
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
            ImageView swordView = new ImageView();
            swordView.setImage(swordImg);
            swordView.relocate(sword.getPoint().getX() * tileSize, sword.getPoint().getY() * tileSize);
            sword.setImageView(swordView);
            groupTiles.getChildren().add(swordView);
        }

        for (Teleporter teleporter : teleporters) {
            Image teleporterImage = new Image(teleporterBlockPath);
            ImageView imageView = new ImageView();
            imageView.setImage(teleporterImage);
            imageView.relocate(teleporter.getStartPoint().getX() * tileSize, teleporter.getStartPoint().getY() * tileSize);
            imageView.toFront();
            groupTiles.getChildren().add(imageView);
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

    public void respawnMobs() {
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
