package com.company;

import static com.company.Main.playerTilePath;

public class PlayerModel extends Character {

    private boolean hasSword = false;
    private int health;

    public int getHealth() { return health; }

    public PlayerModel() {
        super(playerTilePath);
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
