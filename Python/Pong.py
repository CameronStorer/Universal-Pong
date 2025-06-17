######### import pygame library #############################
import pygame as pygame
from time import sleep

######### define classes ####################################

# define class for rect objects
class rect:
    def __init__(self, x=10, y=10, width=5, height=5, color=(255, 0, 0)):
        self._x = x
        self._y = y
        self._width = width
        self._height = height
        self._color = color
        self._rect = 0

    # function to initialize the appropriate rect
    def initialize(self):
        self._rect = pygame.Rect(self._x, self._y, self._width, self._height)

    # function to draw the rect onto the screen
    def draw(self):
        pygame.draw.rect(screen, self._color, self._rect)

    # getters and setters
    @property
    def x(self):
        return self._rect.x

    @x.setter
    def x(self, value):
        if (value > 0 and value < (500 - self._rect.width)):
            self._rect.x = value
    @property
    def y(self):
        return self._rect.y

    @y.setter
    def y(self, value):
        if (value > 0 and value < (350 - self._rect.height)):
            self._rect.y = value

    @property
    def width(self):
        return self._rect.width

    @width.setter
    def width(self, value):
        if (value > 0):
            self._rect.width = value
    @property
    def height(self):
        return self._rect.height

    @height.setter
    def height(self, value):
        if (value > 0):
            self._rect.height = value
    @property
    def color(self):
        return self._rect.color

    @color.setter
    def color(self, value):
        self._rect.color = value
    @property
    def rect(self):
        return self._rect

    @rect.setter
    def rect(self, value):
        self._rect = value

######### initialize pygame #################################
pygame.init()
pygame.font.init() # initialize the pygame font library
font_choice = pygame.font.SysFont('Arial', 30) # choose font attributes
######### set some constants ################################
screen_x = 500
screen_y = 350
screen = pygame.display.set_mode((screen_x, screen_y)) # dimensions
pygame.display.set_caption("Pong") # window title
screen_background_color = "black"
clock = pygame.time.Clock() # initialize a pygame clock object
FPS = 60 # the frame rate
score = [0,0] # define a score
ball_velocity_x = ball_velocity_y = 2 # the ball's current velocity
cooldown_time = 0 # initialize a time variable
opponent_speed = 4 # initialize a variable for the opponent's speed
text = font_choice.render("0, 0", True, (255, 255, 255)) # set default text
paused = False # the game starts not paused
running = True # default running state

######### initialize some rect objects ######################

# create the first paddle
opponent = rect(50, 40, 10, 60, "red")
opponent.initialize()
opponent.draw()
# create the ball
ball = rect(250, 50, 12, 12, "white")
ball.initialize()
ball.draw()
# create the second paddle
player = rect(450, 40, 10, 60, "blue")
player.initialize()
player.draw()

########## main while loop ###################################
while running:
    # if the user exits the window, kill the program
    for event in pygame.event.get():
        if (event.type == pygame.QUIT):
            running = False
    # obtain state of every key currently pressed
    keys = pygame.key.get_pressed()
    # add responsiveness to keybinds
    if keys[pygame.K_UP]:
        player.y -= 5
    if keys[pygame.K_DOWN]:
        player.y += 5

    if (not paused):
        # obtain the current time
        current_time = pygame.time.get_ticks()

        # update the state of the ball
        ball.x += ball_velocity_x
        ball.y += ball_velocity_y

        # if ball hits a vertical wall
        if (ball.y < ball.height) or (ball.y < 355 and ball.y > 342 - ball.height):
            ball_velocity_y *= -1

        # if ball hits a paddle
        elif (ball.rect.colliderect(player.rect) or (ball.rect.colliderect(opponent.rect))):
            # add a cool down to prevent strange happenings
            if (current_time - cooldown_time > 300):
                cooldown_time = current_time
                ball_velocity_x *= -1
                if (ball_velocity_y > 0):
                    ball_velocity_y += 1
                else: 
                    ball_velocity_y -= 1
                if (ball_velocity_x > 0):
                    ball_velocity_x += 1
                else:
                    ball_velocity_x -= 1
            
        # safety net velocity
        if (ball_velocity_x >= 21): # if the ball moves too fast
            ball_velocity_x = 20
        elif (ball_velocity_x <= -21):
            ball_velocity_x = -20
        if (ball_velocity_y >= 21):
            ball_velocity_y = 20
        elif (ball_velocity_y <= -21):
            ball_velocity_y = -20
        if (ball_velocity_x < 2 and ball_velocity_x > -2): # if the ball moves too slow
            if (ball_velocity_x > 0):
                ball_velocity_x = 2
            else:
                ball_velocity_x = -2
        if (ball_velocity_y < 2 and ball_velocity_y > -2):
            if (ball_velocity_y > 0):
                ball_velocity_y = 2
            else:
                ball_velocity_y = -2
        if (opponent_speed < 4): # opponent speed minimum
            opponent_speed = 4
        if (opponent_speed > 9): # opponent speed maximum
            opponent_speed = 9
    
        # if the paddles miss it
        if (ball.x < 30) or (ball.x > 470):
            # player scores
            if (ball.x < 35):
                score[1] += 1
                player_advantage = score[1] - score[0]
                text = font_choice.render(f"{score[0]}, {score[1]}", True, (255, 255, 255)) # update text
                if (player_advantage > 1): # add a difficulty changer based on current situation
                    opponent_speed += 1
            # opponent scores
            if (ball.x > 465):
                score[0] += 1
                player_advantage = score[1] - score[0]
                text = font_choice.render(f"{score[0]}, {score[1]}", True, (255, 255, 255)) # update text
                if (player_advantage < -1):
                    opponent_speed -= 1
            ball.x = 250 - ball.width
            ball.y = 175 - ball.height
            if (ball_velocity_x > 1 or ball_velocity_x < -1):
                ball_velocity_x = ball_velocity_x // 2
                ball_velocity_y = ball_velocity_y //2

        # update the state of the opponent
        if (ball.y - 2 * ball.height > opponent.y):
            opponent.y += opponent_speed
        elif (ball.y - 2 * ball.height < opponent.y):
            opponent.y -= opponent_speed

        # refill and redraw all window elements for the frame
        screen.fill(screen_background_color) # set the window background color
        player.draw()# draw rects
        ball.draw()
        opponent.draw()
        screen.blit(text, (225, 0)) # render text

    # update the display
    pygame.display.flip()
    # update the clock
    clock.tick(FPS)

    # when someone wins the game
    if (score[0] == 15 or score[1] == 15):
        if (score[0] > score[1]):
            text = font_choice.render("Opponent wins", True, (255, 255, 255)) # update text based on who wins
        else:
            text = font_choice.render("You win", True, (255, 255, 255))
        screen.fill(screen_background_color) # set the window background color
        screen.blit(text, (180, 145)) # insert text
        paused = True # pause the game

# exit pygame
pygame.quit()
