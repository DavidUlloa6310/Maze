package com.company;

import javafx.scene.image.Image;
import static com.company.Main.teleporterBlockPath;

public class Teleporter {
    private Point startPoint;
    private Image image;
    private Point teleportPoint;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getTeleportPoint() {
        return teleportPoint;
    }

    public Teleporter(Point startPoint, Point teleportPoint) {
        this.startPoint = startPoint;
        this.image = new Image(teleporterBlockPath);
        this.teleportPoint = teleportPoint;
    }

}
