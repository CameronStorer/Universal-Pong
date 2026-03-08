// Goal: create near-identical pong implementation in JS

// Define rectangle class
class Square {
    // class constructor
    constructor(x=10, y=10, width=5, height=5, color="rgb(255,0,0)"){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.velocityX = 0;
        this.velocityY = 0;
        this.type = 0;
    }

    // function to help with collision detection
    getBounds() {
        return {
            x: this.x,
            y: this.y,
            width: this.width,
            height: this.height
        };
    }

    // getters
    getX(){
        return this.x;
    }
    getY(){
        return this.y;
    }
    getWidth(){
        return this.width;
    }
    getHeight(){
        return this.height;
    }
    getColor(){
        return this.color;
    }
    getVelocityX(){
        return this.velocityX;
    }
    getVelocityY(){
        return this.velocityY;
    }
    // setters
    setX(value){
        if (value > 0 && value < (500 - this.width)){
            this.x = value}
    }
    setY(value){
        if (value > 0 && value < (350 - this.height)){
            this.y = value;
        } else if (this.type == 1){
            if (value <= 0){
                this.setVelocityY(this.getVelocityY() * -1);
            } else if (value >= 350 - this.height){
                this.setVelocityY(this.getVelocityY() * -1);
            }
        }
    }
    setWidth(value){
        if (value > 0){
            this.width = value;
        }
    }
    setHeight(value){
        if (value > 0){
            this.height = value;
        }    
    }
    setColor(value){
        this.color = value;
    }
    setVelocityX(value){
        this.velocityX = value;
    }
    setVelocityY(value){
        this.velocityY = value;
    }
    setType(value){
        this.type=value;
    }

    // func to draw rect onto screen
    draw(ctx) {
        ctx.fillStyle = this.color;
        ctx.fillRect(this.x, this.y, this.width, this.height);
    }
}

// Create constants and variables
// html canvas + canvas context
const canvas = document.getElementById('gameCanvas');
const ctx = canvas.getContext('2d');
let scoreVal = document.getElementById('score');
// the game constants
const FPS = 60; // framerate
const SCREENWIDTH = 500; // the game's width
const SCREENHEIGHT = 350; // the game's height
const paused = false; // is the game paused
// the game variables
let score = [0,0]; //score
let cooldownTime = 0.0; // default time variable for cooldown
let lastTime = Date.now() // last frame time
let playerAdvantage = 0; // the difference in scores

// initialize the game objects
// create the first paddle
const opponent = new Square(50, 40, 10, 60, "red");
opponent.setVelocityY(4); // the opposing paddle's default movement speed
// create the ball
const ball = new Square(250, 50, 12, 12, "white");
ball.setType(1);
// create the second paddle
const player = new Square(450, 40, 10, 60, "blue");
// give the ball its initial velocties
ball.setVelocityX(-2); ball.setVelocityY(-2);

// Input listener setup
const keys = {};
document.addEventListener('keydown', e => keys[e.key] = true);
document.addEventListener('keyup', e => keys[e.key] = false);

// function for determining collision
function isColliding(rect1, rect2) {
    return (
        rect1.x < rect2.x + rect2.width &&
        rect1.x + rect1.width > rect2.x &&
        rect1.y < rect2.y + rect2.height &&
        rect1.y + rect1.height > rect2.y
    );
}

// main game loop
function gameloop() {

    // if the game is not paused
    if (!paused){

        // clear canvas each frame
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        // keyboard input => player movement
        if (keys['ArrowUp']){
            player.setY(player.getY() - 5);
        }
        if (keys['ArrowDown']){
            player.setY(player.getY() + 5);
        }

        // get the time that has passed since the last frame
        const now = Date.now();
        const deltaTime = (now - lastTime) / 1000; // seconds
        cooldownTime += deltaTime;
        lastTime = now;

        // update the ball's state
        ball.setX(ball.getX() + ball.getVelocityX());
        ball.setY(ball.getY() + ball.getVelocityY());

        // if the ball hits a top/bottom wall
        if ((ball.getY() < ball.getHeight()/2) || (ball.getY() > 345 - (ball.getHeight()))){
            ball.setVelocityY(ball.getVelocityY() * -1);
            console.log("stop");
        }
        console.log(ball.getY());
        console.log(ball.getVelocityY() + "Y");

        // if ball hits a paddle (Collision detection)
        if (isColliding(ball.getBounds(), player.getBounds())
        || isColliding(ball.getBounds(), opponent.getBounds())){
            if (cooldownTime > 0.3){
                cooldownTime = 0;
                if (ball.getVelocityX() > 0){
                    ball.setVelocityX(ball.getVelocityX() + 1);
                } else {
                    ball.setVelocityX(ball.getVelocityX() - 1);
                }
                if (ball.getVelocityY() > 0){
                    ball.setVelocityY(ball.getVelocityY() + 1);
                } else {
                    ball.setVelocityY(ball.getVelocityY() - 1);
                }
                ball.setVelocityX(ball.getVelocityX() * -1);
            }
        }

        // ball velocity safety net
        if (ball.getVelocityX() >= 21){     // if the ball moves to fast
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
        if (ball.getVelocityX() < 2 && ball.getVelocityX() > -2){   // if the ball moves too slow
            if (ball.getVelocityX() >= 0){
                ball.setVelocityX(2);
            }
            else{
                ball.setVelocityX(-2);
            }
        }
        if (ball.getVelocityY() < 2 && ball.getVelocityY() > -2){
            if (ball.getVelocityY() >= 0){
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

        // Display the score
        scoreVal.innerText = "" + score[0] + ", " + score[1];

        // draw rects
        opponent.draw(ctx);
        ball.draw(ctx);
        player.draw(ctx);     

        // required frame method
        requestAnimationFrame(gameloop);
    }
}

// call main loop
gameloop();