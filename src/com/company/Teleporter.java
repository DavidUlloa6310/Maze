package com.company;

public class Teleporter {
    /*
    Represents the Teleporter block.
     */
    private final Point startPoint;
    private final Point teleportPoint;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getTeleportPoint() {
        return teleportPoint;
    }

    public Teleporter(Point startPoint, Point teleportPoint) {
        this.startPoint = startPoint;
        this.teleportPoint = teleportPoint;
    }

}
