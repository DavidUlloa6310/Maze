package com.company;

import javafx.scene.paint.Color;
import static com.company.Main.minotaurTilePath;

public class Minotaur extends Character {

    private int spawnX;
    private int spawnY;

    public int getSpawnX() { return spawnX; }

    public int getSpawnY() { return spawnY; }

    public Minotaur(Point point) {
        super(minotaurTilePath);
        this.spawnX = point.getX();
        this.spawnY = point.getY();
    }

    public Minotaur(Point point, String tilePath) {
        super(tilePath);
        this.spawnX = point.getX();
        this.spawnY = point.getY();
    }

    public void move(Character character, Maze maze) {
        if (character.getX() > this.getX()) {
            this.moveRight(maze);
        } else if (character.getX() < this.getX()) {
            this.moveLeft(maze);
        }

        if (character.getY() > this.getY()) {
            this.moveDown(maze);
        } else if (character.getY() < this.getY()) {
            this.moveUp(maze);
        }

    }

    public void die() {
        this.getImage().toBack();
        this.setDead(true);
    }

}
