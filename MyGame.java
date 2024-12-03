import java.util.Scanner;
import java.util.Random;

interface GameMode {
    void start();
}

class StoryMode implements GameMode {
    public void start() {
        System.out.println("\nStarting the Story mode... Get ready for an adventure!");
    }
}

class SurvivalMode implements GameMode {
    public void start() {
        System.out.println("\nStarting the Survival mode... Can you survive the endless waves?");
    }
}

class NumberGuessingGame implements GameMode {
    private final int MAX_ATTEMPTS = 5;
    private final int numberToGuess;

    public NumberGuessingGame() {
        Random random = new Random();
        this.numberToGuess = random.nextInt(100) + 1;  
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int attemptsLeft = MAX_ATTEMPTS;
        System.out.println("\nStarting the Number Guessing Game... You have " + MAX_ATTEMPTS + " attempts to guess the number between 1 and 100.");

        while (attemptsLeft > 0) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attemptsLeft--;

            if (guess == numberToGuess) {
                System.out.println("Congratulations! You guessed the number correctly.");
                return;
            } else if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }

            if (attemptsLeft > 0) {
                System.out.println("Attempts remaining: " + attemptsLeft);
            } else {
                System.out.println("Sorry, you've run out of attempts. The number was " + numberToGuess + ".");
            }
        }
    }
}

class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Game {
    private Player player;
    private GameMode gameMode;

    public Game(Player player) {
        this.player = player;
    }

    public void chooseGameMode() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPress 1, 2, or 3 to select your game mode.");
            System.out.println("1 - Story");
            System.out.println("2 - Survival");
            System.out.println("3 - Number Guessing Game");
            System.out.print("Select your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                this.gameMode = new StoryMode();
                System.out.println("\nYou have selected Story mode, " + player.getName() + "!");
                break;
            } else if (choice.equals("2")) {
                this.gameMode = new SurvivalMode();
                System.out.println("\nYou have selected Survival mode, " + player.getName() + "!");
                break;
            } else if (choice.equals("3")) {
                this.gameMode = new NumberGuessingGame();
                System.out.println("\nYou have selected Number Guessing Game, " + player.getName() + "!");
                break;
            } else {
                System.out.println("\nInvalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress 'P' to start playing, " + player.getName());
        System.out.print("Press P to start: ");
        String start = scanner.nextLine().trim().toLowerCase();

        if (start.equals("p")) {
            System.out.println("\nStarting the game...");
            gameMode.start();
        } else {
            System.out.println("\nYou didn't press 'P'. Game will not start.");
        }
    }
}


public class MyGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        Player player = new Player(name);
        Game game = new Game(player);

        game.chooseGameMode();
        game.startGame();
    }
}
