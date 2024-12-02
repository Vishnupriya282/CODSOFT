import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            boolean playAgain = true;
            
            while (playAgain) {
                int numberToGuess = random.nextInt(100) + 1;
                int attempts = 0;
                int maxAttempts = 10;
                boolean guessedCorrectly = false;
                
                System.out.println("Guess the number between 1 and 100. You have " + maxAttempts + " attempts.");
                
                while (attempts < maxAttempts && !guessedCorrectly) {
                    System.out.print("Enter your guess: ");
                    int userGuess = scanner.nextInt();
                    attempts++;
                    
                    if (userGuess < numberToGuess) {
                        System.out.println("Too low! Try again.");
                    } else if (userGuess > numberToGuess) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                        guessedCorrectly = true;
                    }
                }
                
                if (!guessedCorrectly) {
                    System.out.println("Sorry, you've used all your attempts. The number was: " + numberToGuess);
                }
                
                System.out.print("Do you want to play again? (yes/no): ");
                String userResponse = scanner.next();
                playAgain = userResponse.equalsIgnoreCase("yes");
            }
        }
        System.out.println("Thank you for playing!");
    }
}