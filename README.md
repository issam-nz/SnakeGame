# Snake Game

## **Introduction**

SnakeGame is a JavaFX application that implements the classic game of Snake. The game involves controlling a snake on a game board, aiming to eat food and grow in length while avoiding collisions with walls and the snake's own body.

## **Classes**

### **SnakeGame**

The **`SnakeGame`** class extends the **`Application`** class from JavaFX and serves as the entry point for the Snake Game application. It contains the main game logic, initialization, and event handling.

### Fields

- **`speed`**: An integer representing the speed of the game.
- **`width`**: An integer representing the width of the game board.
- **`height`**: An integer representing the height of the game board.
- **`cornersize`**: An integer representing the size of each corner in the game.
- **`direction`**: A **`Dir`** enum representing the current direction of the snake.
- **`gameOver`**: A boolean indicating whether the game is over or not.
- **`rand`**: An instance of the **`Random`** class for generating random numbers.
- **`snake`**: An instance of the **`Snake`** class representing the snake in the game.
- **`food`**: An instance of the **`Food`** class representing the food in the game.
- **`scene`**: An instance of the **`Scene`** class representing the graphical scene of the game.
- **`replayAlertShown`**: A boolean flag to avoid unlimited calls of the replay alert.

### Methods

- **`start(Stage stage)`**: The main entry point of the application. It initializes the game and starts the game loop.
- **`startGame(Stage stage)`**: Initializes and starts the game loop. Sets up the game window, canvas, and event handling.
- **`keyPressed()`**: Event handler for key presses. Changes the direction of the snake based on the pressed key.
- **`tick(GraphicsContext gc)`**: The game loop that updates the game state and redraws the game elements on the canvas.
- **`newFood()`**: Generates a new food position for the snake to eat.
- **`replayGame()`**: Resets the game state to restart the game.

### **Corner**

The **`Corner`** class represents a single corner (part) of the snake in the game.

### Fields

- **`x`**: An integer representing the x-coordinate of the corner.
- **`y`**: An integer representing the y-coordinate of the corner.

### Constructor

- **`Corner(int x, int y)`**: Initializes a new **`Corner`** object with the specified coordinates.

### **Snake**

The **`Snake`** class represents the snake in the game, which is a collection of **`Corner`** objects.

### Constructor

- **`Snake()`**: Initializes a new **`Snake`** object with an initial length of 3.

### Methods

- **`addxy(int x, int y)`**: Adds a new **`Corner`** to the snake at the specified coordinates.
- **`updatePositions()`**: Updates the positions of the snake parts, moving each part to the position of the previous part.
- **`moveHead(Dir direction)`**: Moves the head of the snake in the specified direction.
- **`eatFood(int foodX, int foodY)`**: Checks if the snake's head is at the same position as the food and adds a new part to the snake if it is.
- **`checkCollision()`**: Checks if the snake has collided with the walls or its own body.

### **Direction Enumeration (Dir)**

The **`Dir`** enumeration represents the possible directions of movement for the snake in the game. It defines four directions: left, right, up, and down.

### Enum Values:

- **`left`**: Represents the left direction.
- **`right`**: Represents the right direction.
- **`up`**: Represents the up direction.
- **`down`**: Represents the down direction.

### **Food**

The **`Food`** class represents the food item in the game that the snake aims to eat.

### Fields

- **`x`**: An integer representing the x-coordinate of the food.
- **`y`**: An integer representing the y-coordinate of the food.
- **`color`**: A **`Color`** object representing the color of the food.

### Constructor

- **`Food()`**: Initializes a new **`Food`** object with default coordinates (0, 0).

### Methods

- **`getX()`**: Returns the x-coordinate of the food.
- **`getY()`**: Returns the y-coordinate of the food.
- **`setX(int x)`**: Sets the x-coordinate of the food.
- **`setY(int y)`**: Sets the y-coordinate of the food.
- **`getColor()`**: Returns the color of the food.

## **Game Flow**

1. The game starts with an alert prompting the player to click the "Play" button.
2. Upon clicking the "Play" button, the game window is displayed.
3. The game loop starts, and the snake and food are drawn on the canvas.
4. The player can control the snake's movement using the arrow keys.
5. The snake's position and direction are updated in each frame of the game loop.
6. If the snake collides with the walls or its own body, the game ends, and a "Game Over" message is displayed.
7. An alert appears to allow the player to replay the game.
8. If the player chooses to replay, the game state is reset, and a new game starts from step 2.
9. The player can close the game window to exit the application.

## **Conclusion**

This documentation provides an overview of the classes and their functionalities in the SnakeGame application. Understanding the classes and their interactions will help in further development, debugging, and customization of the game.
