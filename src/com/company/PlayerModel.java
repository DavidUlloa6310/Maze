package com.company;

import javafx.scene.paint.Color;

public class PlayerModel extends Character {

    private boolean hasSword = false;

    public PlayerModel(Color color) {
        super(color);
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
