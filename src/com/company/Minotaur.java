package com.company;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static com.company.Main.minotaurTilePath;
import static com.company.Main.tileSize;

public class Minotaur extends Character {

    private final int spawnX;
    private final int spawnY;

    private boolean isDead = false;
    public boolean isDead() { return isDead; }
    public void setDead(boolean isDead) { this.isDead = isDead; }

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

    public void generateModel(Pane pane) {
        this.setX(spawnX);
        this.setY(spawnY);
        this.getImage().relocate(spawnX * tileSize,spawnY* tileSize);
        pane.getChildren().add(this.getImage());
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

    public boolean checkDamage(Stage stage, PlayerModel player) {
        if (this.getX() == player.getX() && this.getY() == player.getY()) {
            if (player.hasSword()) {
                player.removeSword();
                this.die();
                return false;
            } else {
                if (player.getHealth() > 1) {
                    player.changeHealth(-1);
                    return true;
                } else {
                    stage.close();
                }
            }
        }
        return false;
    }

}
