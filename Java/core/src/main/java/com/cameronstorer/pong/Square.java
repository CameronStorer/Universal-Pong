package com.cameronstorer.pong;

import com.badlogic.gdx.utils.Null;

// declare the Square class
public class Square {
    
    // instance variables
    private int x;
    private int y;
    private int width;
    private int height;
    private int velocityX, velocityY = 0;

    // constructor
    public Square(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // no-argument constructor
    public Square(){
        this.x = 0;
        this.y = 0;
        this.width = 10;
        this.height = 10;
    }

    // methods
    public void debug(String a){
        System.out.println("this is a test:" + a);
    }

    // Getters and setters
    // x position
    public int getX() {
        return this.x;
    }
    public void setX(int newX) {
        if (newX > 0 && newX < (500 - this.width)){
            this.x = newX;
        }
    }
    // y position
    public int getY() {
        return this.y;
    }
    public void setY(int newY) {
        if (newY > 0 && newY < (350 - this.height)){
            this.y = newY;
        }
    }
    // width
    public int getWidth() {
        return this.width;
    }

    public void setWidth(int newWidth) {
        if (newWidth > 0){
            this.width = newWidth;
        }
    }
    // height
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int newHeight) {
        if (newHeight > 0){
            this.height = newHeight;
        }
    }
    // velocity in x direction
    public int getVelocityX() {
        return this.velocityX;
    }
    public void setVelocityX(int newVelocityX) {
        this.velocityX = newVelocityX;
    }
    // velocity in y direction
    public int getVelocityY() {
        return this.velocityY;
    }
    public void setVelocityY(int newVelocityY) {
        this.velocityY = newVelocityY;
    }
}