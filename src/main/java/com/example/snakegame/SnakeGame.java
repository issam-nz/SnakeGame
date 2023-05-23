package com.example.snakegame;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.util.Random;

public class SnakeGame extends Application {
    static int speed = 7;
    static int width = 20;
    static int height = 20;
    static int cornersize = 25;
    static Dir direction = Dir.left;
    static boolean gameOver = false;
    static Random rand = new Random();
    static Snake snake = new Snake(); //The snake (will have the lenght of 3 )
    static Food food = new Food();
    Scene scene;
    private static boolean replayAlertShown = false; //for avoiding the unlimited calls of the replay Alert


    @Override
    public void start(Stage stage) {
        //Alert to start the game - it could be another class too
        Alert playAlert = new Alert(Alert.AlertType.INFORMATION);
        playAlert.setHeaderText(null);
        playAlert.setContentText("Click 'Play' to start the game.");

        //the button to start the game
        ButtonType playButton = new ButtonType("Play");
        playAlert.getButtonTypes().setAll(playButton);

        playAlert.showAndWait();
        if (playAlert.getResult() == playButton) {
            startGame(stage);
        }
    }

    public void startGame(Stage stage) {
        try {
            newFood();

            //Creación del canvas, el panel(VBox) y el graphicsContext (relacionado con canvas)
            VBox root = new VBox();
            Canvas c = new Canvas(width * cornersize, height * cornersize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            //Add AnimationTimer : allows to create a timer, that is called in each frame while it is active.
            AnimationTimer timer = new AnimationTimer() {

                long lastTick = 0; //El tiempo de la última fotogama

                //El metodo handle que se llama 60 veces cada segundo (60 FPS)
                //De otra manera  se llama automáticamente en cada fotograma, y se pasa un parámetro now que representa el tiempo actual en nanosegundos.
                @Override
                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc); //Actualizar la logica y dibujar los elementos (snake y food)
                        return;
                    }

                    long elapsedNanoSeconds = now - lastTick; //El tiempo transcurrido desde el último fotograma
                    long desiredInterval = 1000000000 / speed;

                    if (elapsedNanoSeconds > desiredInterval) {
                        lastTick = now;
                        tick(gc);
                    }

                    //Para asegurar que Si el tiempo transcurrido desde el último fotograma supera el intervalo deseado, se realiza una nueva actualización del juego
                }
            };

            timer.start();

            //Crear Scene con el VBox
            scene = new Scene(root, width * cornersize, height * cornersize);

            //Control de entrada
            keyPressed();

            //add the scene to the stage
            stage.setScene(scene);
            stage.setTitle("SNAKE GAME");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keyPressed() {
        //change the direction according to the pressed key
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.UP && direction != Dir.down) {
                direction = Dir.up;
            } else if (key.getCode() == KeyCode.LEFT && direction != Dir.right) {
                direction = Dir.left;
            } else if (key.getCode() == KeyCode.DOWN  && direction != Dir.up) {
                direction = Dir.down;
            } else if (key.getCode() == KeyCode.RIGHT && direction != Dir.left) {
                direction = Dir.right;
            }
        });
    }

    public static void tick(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);

            //if the game is over an Alert appears to replay the game
            if (gameOver && !replayAlertShown) {
                replayAlertShown = true; // Set the flag to indicate that the replay alert has been shown

                Platform.runLater(() -> {
                    Alert replayAlert = new Alert(Alert.AlertType.INFORMATION);
                    replayAlert.setHeaderText(null);
                    replayAlert.setContentText("Game Over. Click 'Replay' to play again.");

                    ButtonType replayButton = new ButtonType("Replay");
                    replayAlert.getButtonTypes().setAll(replayButton);

                    replayAlert.showAndWait();

                    if (replayAlert.getResult() == replayButton) {
                        replayGame();
                        replayAlertShown = false; // Reset the flag when replaying the game
                    }
                });
            }
            return;
        }

        //update the positions of the snake parts
        snake.updatePositions();

        //move the head according to the direction
        snake.moveHead(direction);

        //game over if there is a collision
        if (snake.checkCollision()) gameOver = true;

        //generate new food if it is eaten by the snake
        if(snake.eatFood(food.getX(), food.getY())) newFood();

        // fill the background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * cornersize, height * cornersize);  // Clear the canvas with a black color

        // score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 6), 10, 30);  // Display the score on the canvas

        //display the food
        gc.setFill(food.getColor());
        gc.fillOval(food.getX() * cornersize, food.getY() * cornersize, cornersize, cornersize);

        //display the snake
        for (Corner c : snake) {
            gc.setFill(Color.LIGHTGREEN); //as shadow
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 1, cornersize - 1);
            gc.setFill(Color.GREEN); //as foreground
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 2, cornersize - 2);
        }
    }

    public static void newFood() {
        start: while (true) {
            food.setX(rand.nextInt(width));
            food.setY(rand.nextInt(width));

            for (Corner c: snake) {
                //avoid generating the food in a corner occupied by the snake
                if (c.x == food.getX() && c.y == food.y) continue start;
            }
            speed++; //increase the speed every time the snake eats the food
            break;
        }
    }

    private static void replayGame() {
        snake = new Snake(); // Reset the snake
        gameOver = false; // Reset the game over flag
        newFood(); // Generate new food
        speed = 7;
    }


}
