package com.company;

import javafx.scene.paint.Color;

public class Minotaur extends Character {
    public Minotaur(Color color) {
        super(color);
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
}
