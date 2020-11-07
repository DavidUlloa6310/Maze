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
    private Group groupRectangles;

    public boolean[][] getMaze() { return maze; }
    public int getPlayerSpawnX() { return playerSpawnX; }
    public int getPlayerSpawnY() { return playerSpawnY; }
    public int getEndX() { return endX; }
    public int getEndY() { return endY; }

    public void setMaze(boolean[][] maze) {
        this.maze = maze;
    }

    public Maze(boolean[][] maze, int playerSpawnX, int playerSpawnY, int endX, int endY) {
        this.maze = maze;
        this.playerSpawnX = playerSpawnX;
        this.playerSpawnY = playerSpawnY;
        this.endX = endX;
        this.endY = endY;
    }

    public void generateMaze(Pane root) {

        groupRectangles = new Group();

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                if (maze[r][c]) {
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
}
