package com.company;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import static com.company.Main.tileSize;
import static com.company.Main.width;
import static com.company.Main.height;

public class Character {
    private Rectangle rectangle;

    private int x;
    private int y;
    private boolean isDead = false;

    private Color color;

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isDead() { return isDead; }

    public void setAlive() {
        this.isDead = false;
    }

    public Character(Color color) {
        this.color = color;
        rectangle = new Rectangle(tileSize, tileSize, color);
    }

    public void generateModel(Pane pane, int x, int y) {
        this.x = x;
        this.y = y;
        rectangle.relocate(x * tileSize, y * tileSize);
        pane.getChildren().add(rectangle);
    }

    public void moveRight(Maze maze) {
        if (this.x != width - 1 && !maze.getMaze()[this.y][this.x + 1]) {
            this.x++;
            rectangle.relocate(x * tileSize, y * tileSize);
        }
    }

    public void moveDown(Maze maze) {
        if (this.y != height - 1 && !maze.getMaze()[this.y + 1][this.x]) {
            this.y++;
            rectangle.relocate(x * tileSize, y * tileSize);
        }
    }

    public void moveLeft(Maze maze) {
        if (this.x != 0 && !maze.getMaze()[this.y][this.x - 1]) {
            this.x--;
            rectangle.relocate(x * tileSize, y * tileSize);
        }
    }

    public void moveUp(Maze maze) {
        if (this.y != 0 && !maze.getMaze()[this.y - 1][this.x]) {
            this.y--;
            rectangle.relocate(x * tileSize, y * tileSize);
        }
    }

    public void respawn(int respawnX, int respawnY) {
        rectangle.relocate(respawnX * tileSize, respawnY * tileSize);
        this.x = respawnX;
        this.y = respawnY;
    }

    public void removeModel(Pane root) {
        root.getChildren().remove(rectangle);
        isDead = true;
    }

}
