package com.company;
import com.company.Main.*;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.company.Main.tileSize;

public class Maze {
    private boolean[][] maze;

    private int playerSpawnX, playerSpawnY;

    private int endX, endY;

    private int minotaurX;
    private int minotaurY;

    private int swordX;
    private int swordY;
    private Rectangle sword;

    private Group groupRectangles;

    public boolean[][] getMaze() { return maze; }

    public int getPlayerSpawnX() { return playerSpawnX; }
    public int getPlayerSpawnY() { return playerSpawnY; }

    public int getEndX() { return endX; }
    public int getEndY() { return endY; }

    public int getSwordX() { return swordX; }
    public int getSwordY() { return swordY; }

    public void setMaze(boolean[][] maze) {
        this.maze = maze;
    }

    public Maze(boolean[][] maze, Point playerSpawn, Point end, Point minotaur, Point sword) {
        this.maze = maze;

        this.playerSpawnX = playerSpawn.getX();
        this.playerSpawnY = playerSpawn.getY();

        this.endX = end.getX();
        this.endY = end.getY();

        this.minotaurX = minotaur.getX();
        this.minotaurY = minotaur.getY();

        this.swordX = sword.getX();
        this.swordY = sword.getX();
    }

    public void generateMaze(Pane root) {

        groupRectangles = new Group();

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {

                if (c == swordX && r == swordY) {
                    sword = new Rectangle(tileSize, tileSize, Color.SILVER);
                    sword.relocate(c * tileSize, r * tileSize);
                    groupRectangles.getChildren().add(sword);
                } else if (c == endX && r == endY) {
                    Rectangle endpoint = new Rectangle(tileSize, tileSize, Color.GOLD);
                    endpoint.relocate(c * tileSize, r * tileSize);
                    groupRectangles.getChildren().add(endpoint);
                } else if (maze[r][c]) {
                    Rectangle rectangle = new Rectangle(tileSize, tileSize, Color.WHITE);
                    rectangle.relocate(c * tileSize, r * tileSize);
                    groupRectangles.getChildren().add(rectangle);
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

    public void addMinotaur(Minotaur minotaur) {

    }
}
