package com.cameronstorer.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Pong extends ApplicationAdapter {


    // the game constants
    public static final int FPS = 60; // framerate
    public static final int SCREENWIDTH = 500; // the game's width
    public static final int SCREENHEIGHT = 350; // the game's height
    public boolean paused = false; // is the game paused

    // the game variables
    private int [] score = {0,0}; //score
    private int cooldownTime = 0; // default time variable for cooldown
    private int playerAdvantage = 0; // the difference in scores

    // declare the game objects
    private Square opponent; // create the first paddle
    private Square ball; // create the ball
    private Square player; // create the second paddle
    private ShapeRenderer shapeRenderer; // game rendering tool used to draw shapes

    // called once upon application creation to 
    // initialize the game's objects
    @Override
    public void create() {
       // initialize the shapeRenderer
       shapeRenderer = new ShapeRenderer(); 

        // initialize the game objects
        // create the first paddle
        opponent = new Square(50, 40, 10, 60);
        opponent.setVelocityY(4); // the opposing paddle's default movement speed
        // create the ball
        ball = new Square(250, 50, 12, 12);
        // create the second paddle
        player = new Square(450, 40, 10, 60);

       // set the game window size
       Gdx.graphics.setWindowedMode(SCREENWIDTH, SCREENHEIGHT);
    
        // give the ball its initial velocties
        ball.setVelocityX(-2); ball.setVelocityY(-2);
    }

    // called repeatedly many times a second (every frame)
    // where the game logic is updated (the main loop)
    // object movement, collision, and input is drawn
    @Override
    public void render() {

        // if the game is not paused
        if (!paused){

            // game logic updates
            // handle player input
            if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
                player.setY(player.getY() - 5);
            }
            if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) {
                player.setY(player.getY() + 5);
            }

            // get the time that has passed since the last frame
            float deltaTime = Gdx.graphics.getDeltaTime();

            // update the ball's state
            ball.setX(ball.getX() + ball.getVelocityX());
            ball.setY(ball.getY() + ball.getVelocityY());

            // if the ball hits a vertical wall
            if ((ball.getY() < 3) || (ball.getY() > 335)){
                ball.setVelocityY(ball.getVelocityY() * -1);
                System.out.println("stop");
            }
            System.out.println(ball.getY());

            // if ball hits a paddle
            // add collision detection?
            // way of obtaining current time?
            // way of rendering text?

            // ball velocity safety net
            if (ball.getVelocityX() >= 21){         // if the ball moves to fast
                ball.setVelocityX(20);
            }
            else if (ball.getVelocityX() <= -21){
                ball.setVelocityX(-20);
            }
            if (ball.getVelocityY() >= 21){
                ball.setVelocityY(20);
            }
            else if (ball.getVelocityY() <= -21){
                ball.setVelocityY(-20);
            }
            if (ball.getVelocityX() < 2 && ball.getVelocityX() > -2){   // if the ball moves to slow
                if (ball.getVelocityX() > 0){
                    ball.setVelocityX(2);
                }
                else{
                    ball.setVelocityX(-2);
                }
            }
            if (ball.getVelocityY() < 2 && ball.getVelocityY() > -2){
                if (ball.getVelocityY() > 0){
                    ball.setVelocityY(2);
                }
                else{
                    ball.setVelocityY(-2);
                }
            }
            if (opponent.getVelocityY() < 4){ // opponent speed minimum
                opponent.setVelocityY(4);
            }
            if (opponent.getVelocityY() > 9){ // opponent speed maximum
                ball.setVelocityY(9);
            }

            // if a paddle misses the ball
            if ((ball.getX() < 30) || (ball.getX() > 470)){
                // if the player scores
                if (ball.getX() < 35){
                    score[1] += 1;
                    playerAdvantage = score[1] - score[0];
                    // update the text here
                    if (playerAdvantage > 1){
                        opponent.setVelocityX(opponent.getVelocityX() + 1);
                    }
                }
                // if the oppenent scores
                if (ball.getX() > 465){
                    score[0] += 1;
                    playerAdvantage = score[1] - score[0];
                    // update the text here
                    if (playerAdvantage < -1){
                        opponent.setVelocityX(opponent.getVelocityX() - 1);
                    }
                }
                // reset the ball's position
                ball.setX(250 - ball.getWidth());
                ball.setY(175 - ball.getHeight());
                if (ball.getVelocityX() > 1 || ball.getVelocityX() <-1){
                    ball.setVelocityX(ball.getVelocityX() / 2);
                    ball.setVelocityY(ball.getVelocityY() / 2);
                }
            }

            // update the state of the opponent
            if (ball.getY() - 2 * ball.getHeight() > opponent.getY()){
                opponent.setY(opponent.getY() + opponent.getVelocityY());
            }
            else if (ball.getY() - 2 * ball.getHeight() < opponent.getY()){
                opponent.setY(opponent.getY() - opponent.getVelocityY());
            }

            // fill the screen with the background color
            Gdx.gl.glClearColor(0, 0, 0, 1); // Black in RGBA
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // Draw the shapes
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // so that the shapes are filled
            // Draw the ball
            shapeRenderer.setColor(Color.WHITE); // change the color
            shapeRenderer.rect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
            // Draw opponent
            shapeRenderer.setColor(Color.RED); // the color of the shapes
            shapeRenderer.rect(opponent.getX(), opponent.getY(), opponent.getWidth(), opponent.getHeight());
            // Draw the player
            shapeRenderer.setColor(Color.BLUE); // change the color again
            shapeRenderer.rect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
            shapeRenderer.end(); // stop drawing
        }



    }

    // called at the end of the program (when it is destroyed)
    // disposes of any disposable resources to prevent memory leak
    // (e.g. textures and sounds)
    @Override
    public void dispose() {
        // dispose of the shape renderer
        if (shapeRenderer != null){ // ensure the shapeRenderer object exists
            shapeRenderer.dispose();
        }
    }
}