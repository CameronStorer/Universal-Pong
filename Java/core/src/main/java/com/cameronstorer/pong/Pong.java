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
    private int cooldownTime = 0; // default time variable for cooldown
    private int opponentSpeed = 4; // the opposing paddles movements speed

    // initialize the game objects
    // create the first paddle
    Square opponent = new Square(50, 40, 10, 60, "red");
    // create the ball
    Square ball = new Square(250, 50, 12, 12, "white");
    // create the second paddle
    Square player = new Square(450, 40, 10, 60, "blue");

    // called once upon application creation to 
    // initialize the game's objects
    @Override
    public void create() {
       // initialize the shapeRenderer
       shapeRenderer = new ShapeRenderer(); 

       // set the game window size
       Gdx.graphics.setWindowedMode(SCREENWIDTH, SCREENHEIGHT);
    
        // give the ball its initial velocties
        ball.setVelocityX(2); ball.setVelocityY(2);
    }

    // called repeatedly many times a second (every frame)
    // where the game logic is updated (the main loop)
    // object movement, collision, and input is drawn
    @Override
    public void render() {

        // get the time that has passed since the last frame
        float deltaTime = Gdx.graphics.getDeltaTime();

        // the game's logic
        // handle player input
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
            player 
        }

    }

    // called at the end of the program (when it is destroyed)
    // disposes of any disposable resources to prevent memory leak
    // (e.g. textures and sounds)
    @Override
    public void dispose() {
    }
}