package com.example.snakegame;

import javafx.scene.paint.Color;

public class Food {
    int x;
    int y;

    Color color = Color.RED;

    //constructor doesn't have anything
    Food() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }
}
