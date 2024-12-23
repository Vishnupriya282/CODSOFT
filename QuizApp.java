import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static final Question[] quizData = {
            new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, "Paris"),
            new Question("What is 5 + 7?", new String[]{"10", "11", "12", "13"}, "12")
    };
    private static int currentQuestion = 0;
    private static int score = 0;
    private static final int TOTAL_TIME = 10; 
    private static boolean isTimeUp = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestion < quizData.length) {
            isTimeUp = false;
            System.out.println("\nQuestion " + (currentQuestion + 1) + ": " + quizData[currentQuestion].getQuestion());
            String[] options = quizData[currentQuestion].getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    isTimeUp = true;
                    System.out.println("\nTime's up!");
                    timer.cancel();
                }
            };
            timer.schedule(task, TOTAL_TIME * 1000);

            System.out.print("Enter your choice (1-4): ");
            String input = scanner.nextLine();
            timer.cancel(); 

            if (!isTimeUp) {
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Skipping question.");
                    currentQuestion++;
                    continue;
                }

                if (choice < 1 || choice > options.length) {
                    System.out.println("Invalid choice. Skipping question.");
                } else if (options[choice - 1].equals(quizData[currentQuestion].getAnswer())) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! The correct answer is: " + quizData[currentQuestion].getAnswer());
                }
                currentQuestion++;
            } else {
                currentQuestion++; 
            }
        }

        showResults();
        scanner.close();
    }

    private static void showResults() {
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Final Score: " + score + " / " + quizData.length);
        for (int i = 0; i < quizData.length; i++) {
            System.out.println("Q" + (i + 1) + ": " + quizData[i].getQuestion());
            System.out.println("Correct Answer: " + quizData[i].getAnswer());
        }
    }

    static class Question {
        private final String question;
        private final String[] options;
        private final String answer;

        public Question(String question, String[] options, String answer) {
            this.question = question;
            this.options = options;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public String getAnswer() {
            return answer;
        }
    }
}

