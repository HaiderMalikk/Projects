# PING PONG GAME BY 'HAIDER MALIK' WITH TURTLE ENJOY!!!

# rules: ping pong will be played with 2 players the goal of this game is to hit the ball to the other player with
# the racket without letting the ball go past the racket and hit the boundary at first the ball will be served in a
# random direction but after will be served always to the loser

# the settings will all be configured at the start you will have to reopen the app to select all the settings again
# you however will not have to go through teh start screen again

# ## NOTE! this was my first big animation project I did there for I did not use classes I feel like I have to get
# more familiar wil classes and objects before I use them in a game instead I used functions and called them to run the
# game ( game() )

# a turtle is a shape any shape the ball the rectangle rackets the text on screen are all turtles
# if you see any var with = turtle.Turtle() that means we have created a shape or text(set of shapes)

# Please Turn 'caps lock' Off Before Playing !!! unless the keybindings entered were capitalized

### WHATS GOING TO BE ADDED NEXT ###

# single player mode
# Ability to add custom keybindings
# Properly sorted settings menu to change settings you want and not have to go through all settings with mouse
# classes (we are going to need this as we progress further)

# library's I used
import turtle
import time
import random as rd
import math
import winsound
import sys


# the entire game is placed in a function
def game():
    # before the game there are settings

    # settings tab
    player_settings = turtle.Screen()
    player_settings.title("PING PONG")
    player_settings.setup(480, 300)
    player_settings.bgcolor("green")
    player_settings.tracer(0)
    # settings text
    player_settings_message = turtle.Turtle()
    player_settings_message.speed(0)
    player_settings_message.color("white")
    player_settings_message.penup()
    player_settings_message.hideturtle()
    player_settings_message.goto(0, 80)
    player_settings_message.write("GAME SETTINGS", align="center", font=("courier", 20, "bold"))
    # important message to players
    player1_controls_message = turtle.Turtle()
    player1_controls_message.speed(0)
    player1_controls_message.color("white")
    player1_controls_message.hideturtle()
    player1_controls_message.goto(0, -115)
    player1_controls_message.write("Please Turn 'caps lock' Off Before Playing !!!", align="center",
                                   font=("courier", 10, "bold"))

    # Player controls
    player1_controls = turtle.Turtle()
    player1_controls.speed(0)
    player1_controls.color("white")
    player1_controls.hideturtle()
    player1_controls.goto(-175, -65)
    player1_controls.write("Player 1 \nControls \n\nUp: 'w' \n\nDown: 's'", align="center",
                           font=("courier", 11, "bold"))

    # player names as window inputs (no console inputs text input only accepts strings)
    player1_name = player_settings.textinput("PING PONG", "Player 1 Name: ")
    # clear player 1 controls
    player1_controls.clear()

    # player 2 controls
    player2_controls = turtle.Turtle()
    player2_controls.speed(0)
    player2_controls.color("white")
    player2_controls.hideturtle()
    player2_controls.goto(-175, -65)
    player2_controls.write("Player 2 \nControls \n\nUp: 'i' \n\nDown: 'k'", align="center",
                           font=("courier", 11, "bold"))

    player2_name = player_settings.textinput("PING PONG", "Player 2 Name: ")
    player2_controls.clear()

    # if the player enters a blank set name to default ( if player1_name return false: name = none)
    if player1_name == "":
        player1_name = "Player 1"
    else:
        pass

    if player2_name == "":
        player2_name = "Player 2"
        pass
    else:
        pass

    # clearing the massage giving about caps lock
    player1_controls_message.clear()

    # important notice about score
    score_goal_message = turtle.Turtle()
    score_goal_message.speed(0)
    score_goal_message.color("white")
    score_goal_message.hideturtle()
    score_goal_message.goto(0, -115)
    score_goal_message.write("If no score in entered game will go on forever", align="center",
                             font=("courier", 10, "normal"))

    # score goal setter game will stop once goal is reached ( if nothing is entered game will go on forever)
    score_goal = player_settings.numinput("PING PONG", "Enter Score Goal If You Want One: ")
    # clearing message
    score_goal_message.clear()

    # set a custom speed by entering 2022
    custom_speed = turtle.Turtle()
    custom_speed.speed(0)
    custom_speed.color("white")
    custom_speed.penup()
    custom_speed.hideturtle()
    custom_speed.goto(0, -115)
    custom_speed.write("ENTER '2022' TO ENTER A CUSTOM SPEED", align="center", font=("courier", 10, "normal"))

    # ball speed selector (num input only accepts INT / Float)
    ball_speed_input = player_settings.numinput("PING PONG", "Ball Speed: Enter '1' for Slow '2' for Normal '3' for "
                                                             "Fast '4' for Insane ")

    # matching the users input with corresponding ball speeds
    if ball_speed_input == 1:
        ball_speed = 0.4
    elif ball_speed_input == 2:
        ball_speed = 0.6
    elif ball_speed_input == 3:
        ball_speed = 1
    elif ball_speed_input == 4:
        ball_speed = 1.6
    elif ball_speed_input == 2022:
        ball_speed = player_settings.numinput("PING PONG", "ENTER CUSTOM SPEED! must be float or int"
                                                           " \n'0.4' is slow, " "'0.6' is normal, '1' is fast")
        # if for custom speed, speed == 0, 0 would do nothing so set it to normal (0.6)
        if ball_speed == 0:
            ball_speed = 0.6
    else:
        ball_speed = 0.6
    custom_speed.clear()

    # color inputs select 1 of 3 themes
    theme_color = player_settings.numinput("PING PONG",
                                           "Enter: '1' for DEFAULT THEME '2' for DARK THEME '3' for LIGHT THEME  ")

    # default theme
    if theme_color == 1:
        bg_color = "green"
        border_color = "white"
        p1_color = "red"
        p2_color = "blue"
        score_color = "white"
        ball_color = "orange"

    # dark theme
    elif theme_color == 2:
        bg_color = "black"
        border_color = "white"
        p1_color = "white"
        p2_color = "white"
        score_color = "white"
        ball_color = "white"

    # light theme
    elif theme_color == 3:
        bg_color = "white"
        border_color = "black"
        p1_color = "black"
        p2_color = "black"
        score_color = "black"
        ball_color = "black"
    # default (as nothing was selected)
    else:
        bg_color = "green"
        border_color = "white"
        p1_color = "red"
        p2_color = "blue"
        score_color = "white"
        ball_color = "orange"

    # clear the current setting tab so there is nothing on the screen
    turtle.clearscreen()

    # start of game with all inputs in place
    # game window (aspect ratio = 2:1)
    window = turtle.Screen()
    window.setup(625, 325)
    window.title("Ping Pong")
    # add colour vars
    window.bgcolor(bg_color)
    window.tracer(0)

    # BORDERS
    # border left
    border1 = turtle.Turtle()
    border1.speed(0)
    border1.shape("square")
    border1.color(border_color)
    border1.penup()
    border1.goto(-310, 0)
    border1.shapesize(stretch_wid=16, stretch_len=0.3)

    # border right
    border2 = turtle.Turtle()
    border2.speed(0)
    border2.shape("square")
    border2.color(border_color)
    border2.penup()
    border2.goto(302, 0)
    border2.shapesize(stretch_wid=16, stretch_len=0.3)

    # border up
    border3 = turtle.Turtle()
    border3.speed(0)
    border3.shape("square")
    border3.color(border_color)
    border3.penup()
    border3.goto(-4, 162)
    border3.shapesize(stretch_wid=0.5, stretch_len=30.9)

    # border down
    border4 = turtle.Turtle()
    border4.speed(0)
    border4.shape("square")
    border4.color(border_color)
    border4.penup()
    border4.goto(-4, -154.5)
    border4.shapesize(stretch_wid=0.5, stretch_len=30.9)

    # border divider
    border5 = turtle.Turtle()
    border5.speed(0)
    border5.shape("square")
    border5.color(border_color)
    border5.penup()
    border5.goto(-4, 0)
    border5.shapesize(stretch_wid=0.1, stretch_len=30.9)

    # player 1 shape (the racket)
    player1 = turtle.Turtle()
    player1.speed(0)
    player1.shape("square")
    player1.color(p1_color)
    player1.penup()
    # screen y - 10
    player1.goto(-290, 0)
    player1.shapesize(stretch_wid=3, stretch_len=0.5)

    # function to move the player 1 'UP'
    def moveup_p1():
        # forward(30)
        y = player1.ycor()
        y += 10
        player1.sety(y)
        if y >= 125:
            player1.sety(125)

    # function to move the player 1 'down'
    def movedown_p1():
        # forward(30)
        y = player1.ycor()
        y -= 10
        player1.sety(y)
        if y <= -118:
            player1.sety(-118)

    # player 2 shape (the racket)
    player2 = turtle.Turtle()
    player2.speed(0)
    player2.shape("square")
    player2.color(p2_color)
    player2.penup()
    player2.goto(285, 0)
    player2.shapesize(stretch_wid=3, stretch_len=0.5)

    # function to move the player 2 'UP'
    def moveup_p2():
        # forward(30)
        y = player2.ycor()
        y += 10
        player2.sety(y)
        if y >= 125:
            player2.sety(125)

    # function to move the player 2 'down'
    def movedown_p2():
        # forward(30)
        y = player2.ycor()
        y -= 10
        player2.sety(y)
        if y <= -118:
            player2.sety(-118)

    # window.listen is what listens for keyboard inputs
    window.listen()
    # on 'x' key call 'x' function
    window.onkey(moveup_p2, "i")
    window.onkey(movedown_p2, "k")
    window.onkey(moveup_p1, "w")
    window.onkey(movedown_p1, "s")

    # the Ball Shape is added
    ball = turtle.Turtle()
    ball.speed(0)
    ball.shape("circle")
    # all colors have vars to be changed
    ball.color(ball_color)
    ball.penup()
    ball.goto(0, 0)
    ball.shapesize(stretch_wid=0.5, stretch_len=0.5)

    # BALL SERVER
    # this code serves the ball in one of 4 directions that is random
    # 1. up and right (x, y) 2. down and right (x, -y)
    # 3. up and left (-x, y) 4. down and left (-x, -y)

    # serve_side_x affects path of balls X cor
    serve_side_x = rd.randint(1, 2)
    if serve_side_x == 1:
        x1 = ball_speed
    if serve_side_x == 2:
        x1 = -ball_speed
    # serve_side_y affects path of balls Y cor
    serve_side_y = rd.randint(1, 2)
    if serve_side_y == 1:
        y1 = ball_speed
    if serve_side_y == 2:
        y1 = -ball_speed

    # settings the number decided by user as the speed and making it positive or negative (random)
    ball_speed_x = x1

    ball_speed_y = y1

    # SCORING
    # both players start at 0 score is added later
    player1_score = 0
    player2_score = 0

    # score banners displays score
    score = turtle.Turtle()
    score.speed(0)
    score.color(score_color)
    score.penup()
    score.hideturtle()
    score.goto(0, 120)
    score.write("%s: %s  %s: %s" % (player1_name, player1_score, player2_name, player2_score), align="center",
                font=("courier", 11, "normal"))

    # Function to check collision (object 1 and 2) by using Pythagoras Therm
    def ball_hit(object1, object2):
        collide = math.sqrt(
            math.pow(object1.xcor() - object2.xcor(), 2) + (math.pow(object1.ycor() - object2.ycor(), 2)))
        # if the Pythagoras Therm is < than 25 'pixels' (if the two shapes are 25 pixels apart)
        if collide < 25:
            return True
        else:
            return False

    # game loop (runs the game forever or until a condition is met)
    time.sleep(0.5)
    while True:
        # updates the screen
        window.update()

        # this function moves the balls x and y coordinates forever until it hits the border
        def move():
            ball.setx(ball.xcor() + ball_speed_x)
            ball.sety(ball.ycor() + ball_speed_y)

        # calling the function to start the balls movement
        move()

        # game borders
        # hitting the top (y cor) will always return the ball in the opposite direction
        # (-) * (+) = - so the ball will go in the negative direction in the y-axis
        if ball.ycor() > 150:
            ball.sety(150)
            # will adapt with speed
            ball_speed_y *= -1
            winsound.PlaySound("sound2", winsound.SND_ASYNC)

        # if ball hit's the bottom just like the top reverse the ball
        # (-) * (-) = + so the ball will go in the positive direction in the y-axis
        if ball.ycor() < -145:
            ball.sety(-145)
            ball_speed_y *= -1
            winsound.PlaySound("sound2", winsound.SND_ASYNC)

        # if the ball passes 310 on the x-axis meaning it has passed the racket (a loss for player 2) this peace of
        # code says: if ball passes racket add 1 to the opposite players score, update the score board, set the ball
        # to 0,0 then move it again (in the loser's direction) after waiting for 1 second
        if ball.xcor() > 310:
            ball.goto(0, 0)
            move()
            player1_score += 1
            score.clear()
            score.write("%s: %s  %s: %s" % (player1_name, player1_score, player2_name, player2_score), align="center",
                        font=("courier", 11, "normal"))
            time.sleep(1)

        # if the ball passes -310 (loss for player 1)
        if ball.xcor() < -310:
            ball.goto(0, 0)
            move()
            player2_score += 1
            score.clear()
            score.write("%s: %s  %s: %s" % (player1_name, player1_score, player2_name, player2_score), align="center",
                        font=("courier", 11, "normal"))
            time.sleep(1)

        # if the 2 objects (ball and racket 1) collide send the ball in the opposite direction
        if ball_hit(player1, ball) and ball.xcor() < -279:
            ball.setx(-279)
            ball_speed_x *= -1
            winsound.PlaySound("sound1", winsound.SND_ASYNC)

        # if the 2 objects (ball and racket 2) collide send the ball in the opposite direction
        if ball_hit(player2, ball) and ball.xcor() > 274:
            ball.setx(274)
            ball_speed_x *= -1
            winsound.PlaySound("sound1", winsound.SND_ASYNC)

        # if any of the players score reaches the score entered by the user stop the game
        if player1_score == score_goal or player2_score == score_goal:
            winner = {}
            if player1_score > player2_score:
                winner[0] = player1_name
            if player2_score > player1_score:
                winner[0] = player2_name

            # after tell the user who won then give an option to play again or quit
            def end_screen_window():
                end_screen = window.numinput("%s WINS!!!" % (winner[0]), "Enter '1' to Play Again or '2' to Exit")
                # if play again call the game function if quit close the game (clear screen before proceeding)
                if end_screen == 1:
                    turtle.clearscreen()
                    game()
                # goodbye screen
                if end_screen == 2:
                    turtle.clearscreen()
                    goodbye_screen_2 = turtle.Screen()
                    goodbye_screen_2.setup(300, 300)
                    goodbye_screen_2.bgcolor("black")
                    goodbye_screen_2.tracer(0)

                    goodbye_2 = turtle.Turtle()
                    goodbye_2.speed(0)
                    goodbye_2.color("white")
                    goodbye_2.penup()
                    goodbye_2.hideturtle()
                    goodbye_2.goto(0, 0)
                    goodbye_2.write("GOODBYE", align="center", font=("courier", 20, "bold"))

                    # after saying goodbye wait 1 sec before closing app
                    time.sleep(1)
                    sys.exit()
                # keep repeating the options until a valid response in entered
                else:
                    end_screen_window()

            end_screen_window()


# Start Screen
start_screen = turtle.Screen()
start_screen.title("PING PONG")
start_screen.setup(300, 300)
start_screen.bgcolor("red")
start_screen.tracer(0)


# window prompt welcoming the user and giving 2 options to play game to quit (close the app)
def start():
    intro = turtle.Turtle()
    intro.speed(0)
    intro.color("white")
    intro.penup()
    intro.hideturtle()
    intro.goto(0, 80)
    intro.write("Welcome To Ping Pong", align="center", font=("courier", 15, "bold"))

    gameinfo = turtle.Turtle()
    gameinfo.speed(0)
    gameinfo.color("white")
    gameinfo.penup()
    gameinfo.hideturtle()
    gameinfo.goto(-15, -125)
    gameinfo.write("This program was created by 'Haider Malik'\n using turtle in python enjoy!", align="center",
                   font=("arial", 9, "normal"))

    # if start call game or else if quit close the app
    start_game = start_screen.textinput("welcome", "Enter 'start' to play or 'quit' to close")
    if start_game == "start":
        gameinfo.clear()
        intro.clear()
        game()
    # clear previous screens display goodbye screen wait 3 seconds then close the program
    if start_game == "quit":
        gameinfo.clear()
        intro.clear()

        goodbye_screen = turtle.Screen()
        goodbye_screen.setup(300, 300)
        goodbye_screen.bgpic("emoji.png")
        goodbye_screen.bgcolor("white")
        goodbye_screen.tracer(0)

        goodbye = turtle.Turtle()
        goodbye.speed(0)
        goodbye.color("red")
        goodbye.penup()
        goodbye.hideturtle()
        goodbye.goto(0, 80)
        goodbye.write("GOODBYE", align="center", font=("courier", 20, "bold"))
        # message 2
        goodbye.speed(0)
        goodbye.color("red")
        goodbye.penup()
        goodbye.hideturtle()
        goodbye.goto(0, -110)
        goodbye.write("Program Will Close Shortly", align="center", font=("arial", 12, "normal"))
        time.sleep(3)
        goodbye.clear()
        sys.exit()
    # keep repeating the prompt until a valid input in entered
    else:
        gameinfo.clear()
        intro.clear()
        start()


# calling the first window starts the whole program
start()

# END! #
