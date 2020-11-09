package com.company;

import javafx.scene.paint.Color;

public class PlayerModel extends Character {

    private boolean hasSword = false;

    public PlayerModel(String imagePath) {
        super(imagePath);
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
