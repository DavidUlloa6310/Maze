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

    public void moveRight() {
        rectangle.relocate(x + 25, y);
        this.x = x + 25;
    }

    public void moveDown() {
        rectangle.relocate(x, y + 25);
        this.y = y + 25;
    }

    public void moveLeft() {
        rectangle.relocate(x - 25, y);
        this.x = x - 25;
    }

    public void moveUp() {
        rectangle.relocate(x, y - 25);
        this.y = y - 25;
    }

}
