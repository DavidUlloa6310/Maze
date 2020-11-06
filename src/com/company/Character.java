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

    public void moveRight(boolean[][] maze) {
        if (this.x / 25  + 1 < 24) {
            rectangle.relocate(x + 25, y);
            this.x = x + 25;
        }
    }

    public void moveDown(boolean[][] maze) {
        if (this.y / 25 + 1 < 16) {
            rectangle.relocate(x, y + 25);
            this.y = y + 25;
        }
    }

    public void moveLeft(boolean[][] maze) {
        if (this.x / 25 - 1 >= 0) {
            rectangle.relocate(x - 25, y);
            this.x = x - 25;
        }
    }

    public void moveUp(boolean[][] maze) {
        if (this.y / 25 - 1 >= 0) {
            rectangle.relocate(x, y - 25);
            this.y = y - 25;
        }
    }

}
