package com.example.snakegame;

import java.util.ArrayList;

public class Snake extends ArrayList<Corner> {
    Snake() { //Creates corners based on the width and the height
        super(); // create the arraylist

        // add start snake parts
        this.addxy(SnakeGame.width / 2, SnakeGame.height / 2 ); //add initial snake part
        this.addxy(SnakeGame.width / 2, SnakeGame.height / 2 );
        this.addxy(SnakeGame.width / 2, SnakeGame.height / 2 );
    }

    public void addxy(int x, int y) {
        this.add(new Corner(x, y));
    }

    public void updatePositions() {
        // This method updates the positions of snake parts
        for (int i = this.size() - 1; i >= 1; i--) {
            this.get(i).x = this.get(i - 1).x;
            this.get(i).y = this.get(i - 1).y;
        }
    }

    public void moveHead(Dir direction) {
        // This method moves the head (up or down or ... ) according to the direction
        switch (direction) {
            case up:
                this.get(0).y--; // Move the head of the snake up
                break;
            case down:
                this.get(0).y++; // Move the head of the snake down
                break;
            case left:
                this.get(0).x--; // Move the head of the snake to the left
                break;
            case right:
                this.get(0).x++; // Move the head of the snake to the right
                break;
        }
    }

    public boolean eatFood(int foodX, int foodY) {
        //add new part to the snake and return true when the snake eats the food
        if (this.get(0).x == foodX && this.get(0).y == foodY) {
            Corner newPart = this.get(this.size() - 1); // Get the last part of the snake
            this.addxy(newPart.x, newPart.y); // Add a new snake part at the same position as the last part
            return true; // Food was eaten
        }
        return false; // Food was not eaten
    }

    public boolean checkCollision() {
        //check if the snake collides with its body or one of the walls
        //true if collision is detected
        Corner head = this.get(0); // Get the head of the snake

        // Check for collision with walls
        if (head.x < 0 || head.x >= SnakeGame.width || head.y < 0 || head.y >= SnakeGame.height) {
            return true; // Collision with wall detected
        }

        // Check for self-collision
        for (int i = 1; i < this.size(); i++) {
            Corner bodyPart = this.get(i);
            if (head.x == bodyPart.x && head.y == bodyPart.y) {
                return true; // Self-collision detected
            }
        }

        return false; // No collision detected
    }
}