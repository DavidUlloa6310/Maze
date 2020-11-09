package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.company.Main.tileSize;
import static com.company.Main.width;
import static com.company.Main.height;

public class Character {
    private ImageView image;

    private int x;
    private int y;
    private boolean isDead = false;

    private Color color;

    public int getX() { return x; }
    public int getY() { return y; }
    public ImageView getImage() { return image; }

    public boolean isDead() { return isDead; }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public Character(String imagePath) {
        Image temp = new Image(imagePath);
        image = new ImageView();
        image.setImage(temp);
    }

    public void generateModel(Pane pane, int x, int y) {
        this.x = x;
        this.y = y;
        image.relocate(x * tileSize, y * tileSize);
        pane.getChildren().add(image);
    }

    public void moveRight(Maze maze) {
        if (this.x != width - 1 && !maze.getMaze()[this.y][this.x + 1]) {
            this.x++;
            image.relocate(x * tileSize, y * tileSize);
        }
    }

    public void moveDown(Maze maze) {
        if (this.y != height - 1 && !maze.getMaze()[this.y + 1][this.x]) {
            this.y++;
            image.relocate(x * tileSize, y * tileSize);
        }
    }

    public void moveLeft(Maze maze) {
        if (this.x != 0 && !maze.getMaze()[this.y][this.x - 1]) {
            this.x--;
            image.relocate(x * tileSize, y * tileSize);
        }
    }

    public void moveUp(Maze maze) {
        if (this.y != 0 && !maze.getMaze()[this.y - 1][this.x]) {
            this.y--;
            image.relocate(x * tileSize, y * tileSize);
        }
    }

    public void respawn(int respawnX, int respawnY) {
        image.relocate(respawnX * tileSize, respawnY * tileSize);
        image.toFront();
        this.x = respawnX;
        this.y = respawnY;
    }

    public void removeModel(Pane root) {
        root.getChildren().remove(image);
        isDead = true;
    }

}
