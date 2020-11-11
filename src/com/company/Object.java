package com.company;

import javafx.scene.image.ImageView;

public class Object {
    private Point point;
    private ImageView imageView;
    private boolean isGone;

    public Point getPoint() { return point; }

    public ImageView getImageView() { return imageView; }
    public void setImageView(ImageView imageView) { this.imageView = imageView; }

    public boolean isGone() { return isGone; }
    public void setGone(boolean gone) { isGone = gone; }

    public Object(Point point) {
        this.point = point;
    }
}
