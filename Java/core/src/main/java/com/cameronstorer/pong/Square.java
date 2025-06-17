package com.cameronstorer.pong;

// declare the Square class
public class Square {
    
    // instance variables
    private int x;
    private int y;
    private int width;
    private int height;
    private int color;

    // constructor
    public Square(int x, int y, int width, int height, int color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // no-argument constructor
    public Square(){
        this.x = 0;
        this.y = 0;
        this.width = 10;
        this.height = 10;
        this.color = 0;
    }

    // methods
    public void debug(String a){
        System.out.println("this is a test:" + a);
    }

    // Getters and setters
    // x position
    public int getX() {
        return x;
    }
    public void setX(int newX) {
        if (newX > 0){
            this.x = newX;
        }
    }
    // y position
    public int getY() {
        return y;
    }
    public void setY(int newY) {
        if (newY > 0){
            this.y = newY;
        }
    }
    // width
    public int getWidth() {
        return width;
    }

    public void setWidth(int newWidth) {
        if (newWidth > 0){
            this.width = newWidth;
        }
    }
    // color
    public int getColor() {
        return color;
    }
    public void setColor(int newColor) {
        if (newColor > 0){
            this.color = newColor;
        }
    }
}