package com.company;

import javafx.scene.paint.Color;
import static com.company.Main.minotaurTilePath;

public class Minotaur extends Character {

    public Minotaur() {
        super(minotaurTilePath);
    }

    public Minotaur(String tilePath) {
        super(tilePath);
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
