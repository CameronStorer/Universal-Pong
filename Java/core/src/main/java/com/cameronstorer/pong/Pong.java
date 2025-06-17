package com.cameronstorer.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Pong extends ApplicationAdapter {

    // game rendering tool
    private ShapeRenderer shapeRenderer; // used to draw shapes

    // the game constants
    public static final int FPS = 60; // framerate
    public static final int SCREENWIDTH = 500; // the game's width
    public static final int SCREENHEIGHT = 350; // the game's height
    public boolean paused = false; // is the game paused

    // the game variables
    private int [] score = {0,0}; //score
    private int ballVelocityX, ballVelocityY = 2; //ball velocity
    private int cooldownTime = 0; // default time variable for cooldown
    private int opponentSpeed = 4; // the opposing paddles movements speed


    // called once upon application creation to 
    // initialize the game's objects
    @Override
    public void create() {
       // initialize the shapeRenderer
       shapeRenderer = new ShapeRenderer(); 

       // set the game window size
       Gdx.graphics.setWindowedMode(SCREENWIDTH, SCREENHEIGHT);

       // a debug test
       Square test = new Square(10, 10, 10, 10, 10);
       test.debug("hi");
    }

    // called repeatedly many times a second (every frame)
    // where the game logic is updated (the main loop)
    // object movement, collision, and input is drawn
    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

    }

    // called at the end of the program (when it is destroyed)
    // disposes of any disposable resources to prevent memory leak
    // (e.g. textures and sounds)
    @Override
    public void dispose() {
    }
}