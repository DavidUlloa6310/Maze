package com.company;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Character {
    private Rectangle rectangle;

    private int x;
    private int y;

    private Color color;

    public int getX() { return x; }
    public int getY() { return y; }

    public Character(Color color) {
        this.color = color;
        rectangle = new Rectangle(25, 25, color);
    }
    public void generateModel(Pane pane, int x, int y) {
        this.x = x;
        this.y = y;
        rectangle.relocate(x, y);
        pane.getChildren().add(rectangle);
    }

    public void moveRight(Maze maze) {
        if (this.x != 23 && !maze.getMaze()[this.y][this.x + 1]) {
            this.x++;
            rectangle.relocate(x * 25, y * 25);
        }
    }

    public void moveDown(Maze maze) {
        if (this.y != 15 && !maze.getMaze()[this.y + 1][this.x]) {
            this.y++;
            rectangle.relocate(x * 25, y * 25);
        }
    }

    public void moveLeft(Maze maze) {
        if (this.x != 0 && !maze.getMaze()[this.y][this.x - 1]) {
            this.x--;
            rectangle.relocate(x * 25, y * 25);
        }
    }

    public void moveUp(Maze maze) {
        if (this.y != 0 && !maze.getMaze()[this.y - 1][this.x]) {
            this.y--;
            rectangle.relocate(x * 25, y * 25);
        }
    }

}
