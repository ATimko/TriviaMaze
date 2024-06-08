package model;

import java.util.Scanner;

public class testMaze {
    public static void main(String[] args) {
        // Create the maze using MazeFactory
        Maze maze = MazeFactory.createMaze();

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean running = true;

        // Display initial room
        System.out.println("Welcome to the Maze! You are in room " + maze.getCurrentRoomNumber());
        maze.displayAvailableMoves();

        // Game loop
        while (running) {
            System.out.print("Enter move (UP, DOWN, LEFT, RIGHT) or 'exit' to quit: ");
            input = scanner.nextLine().toUpperCase();

            int nextRoom = -1;
            switch (input) {
                case "UP":
                    nextRoom = maze.getCurrentRoomNumber() - 5;
                    break;
                case "DOWN":
                    nextRoom = maze.getCurrentRoomNumber() + 5;
                    break;
                case "LEFT":
                    nextRoom = maze.getCurrentRoomNumber() - 1;
                    break;
                case "RIGHT":
                    nextRoom = maze.getCurrentRoomNumber() + 1;
                    break;
                case "EXIT":
                    running = false;
                    continue;
                default:
                    System.out.println("Invalid input. Please enter UP, DOWN, LEFT, RIGHT, or exit.");
                    continue;
            }

            boolean needsQuestion = !maze.isVisitedDirection(input);

            if (needsQuestion) {
                Question currentQuestion = maze.getCurrentQuestion();
                if (currentQuestion != null) {
                    System.out.println("Question: " + currentQuestion.getQuestion());
                    String[] choices = currentQuestion.getChoices();
                    String answer = null;
                    if (choices.length > 0) {
                        for (int i = 0; i < choices.length; i++) {
                            System.out.println((i + 1) + ". " + choices[i]);
                        }
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        answer = choices[choice - 1];
                    } else {
                        System.out.print("Enter your answer: ");
                        answer = scanner.nextLine();
                    }

                    boolean isCorrect = currentQuestion.getAnswer().equalsIgnoreCase(answer);
                    if (isCorrect) {
                        if (maze.move(nextRoom, true)) {
                            System.out.println("Correct! You moved to room " + maze.getCurrentRoomNumber());
                        } else {
                            System.out.println("Cannot move to the room due to an error.");
                        }
                    } else {
                        System.out.println("Incorrect! The door is locked.");
                        //maze.lockCurrentDoor(input);
                    }
                }
            } else {
                if (maze.move(nextRoom, true)) {
                    System.out.println("You moved to room " + maze.getCurrentRoomNumber());
                } else {
                    System.out.println("Cannot move " + input + ". Door is locked or invalid move.");
                }
            }

            // Display available moves after each action
            maze.displayAvailableMoves();
        }

        scanner.close();
        System.out.println("Thanks for playing!");
    }
}
