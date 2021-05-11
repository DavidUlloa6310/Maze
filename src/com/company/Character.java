package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static com.company.Main.tileSize;
import static com.company.Main.width;
import static com.company.Main.height;

public class Character {
    /*
    Represents any character which can be used, either the minotaur or player.
     */
    private ImageView image;

    private int x;
    private int y;
    private int steps;

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public int getSteps() { return steps; }

    public ImageView getImage() { return image; }
    public void setImage(Pane pane, Image image) {

        this.image.toBack();
        pane.getChildren().remove(image);

        this.image = new ImageView(image);
        generateModel(pane,this.x,this.y);

        this.image.toFront();
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
            steps++;
        }
    }

    public void moveDown(Maze maze) {
        if (this.y != height - 1 && !maze.getMaze()[this.y + 1][this.x]) {
            this.y++;
            image.relocate(x * tileSize, y * tileSize);
            steps++;
        }
    }

    public void moveLeft(Maze maze) {
        if (this.x != 0 && !maze.getMaze()[this.y][this.x - 1]) {
            this.x--;
            image.relocate(x * tileSize, y * tileSize);
            steps++;
        }
    }

    public void moveUp(Maze maze) {
        if (this.y != 0 && !maze.getMaze()[this.y - 1][this.x]) {
            this.y--;
            image.relocate(x * tileSize, y * tileSize);
            steps++;
        }
    }

    public void respawn(int respawnX, int respawnY) {
        image.relocate(respawnX * tileSize, respawnY * tileSize);
        image.toFront();
        this.x = respawnX;
        this.y = respawnY;
    }

}
