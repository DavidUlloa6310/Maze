package com.company;

import javafx.scene.paint.Color;

public class PlayerModel extends Character {

    private boolean hasSword = false;
    private int health;

    public int getHealth() { return health; }

    public PlayerModel(String imagePath) {
        super(imagePath);
        health = 3;
    }

    public void changeHealth(int amount) {
        health += amount;
    }

    public void addSword() {
        hasSword = true;
    }

    public void removeSword() {
        hasSword = false;
    }

    public boolean hasSword() {
        return hasSword;
    }

}
