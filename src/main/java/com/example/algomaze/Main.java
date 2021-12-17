package com.example.algomaze;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static com.example.algomaze.MenuSelection.*;


public class Main {
    public static void main(String[] args) throws IOException {

            int userSelected;
            //Option selection
            do {
                userSelected = MenuData(); //Menu selection
                switch (userSelected) { //Switch case for menu selection
                    case 1 -> { //If user selects 1, run the maze generation algorithm in easy mode
                        System.out.println("Your difficulty is: ");
                        System.out.println("Labyrinth easy ♥ selected\nthe game will start :");
                        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 5; // Set the x and y size of the maze to 5 by 5
                        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 5;
                        MazeGenerator maze = new MazeGenerator(x, y); // Create a new maze
                        maze.display(); // Display the maze
                        menuFinish();
                    }
                    case 2 -> { //If user selects 2, run the maze generation algorithm in medium mode
                        System.out.println("Your difficulty is: ");
                        System.out.println("Labyrinth medium ★ selected\nthe game will start :");
                        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 10; // Set the x and y size of the maze to 10 by 10
                        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 10;
                        MazeGenerator maze = new MazeGenerator(x, y); //Create a new maze
                        maze.display(); //Display the generated maze
                        menuFinish();
                    }
                    case 3 -> { //If user selects 3, run the maze generation algorithm in hard mode
                        System.out.println("Your difficulty is: ");
                        System.out.println("Labyrinth hard ☠ selected\nthe game will start :");
                        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 20; // Set the x and y size of the maze to 20 by 20
                        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 20;

                        MazeGenerator maze = new MazeGenerator(x, y); //Create a new maze
                        maze.display(); //Display the generated maze
                        menuFinish();
                    }
                    case 4 -> SwingUtilities.invokeLater(() -> { //If user selects 4, resolve the maze, but it's animated
                        JFrame f = new JFrame(); //Create a new JFrame
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close the window on 'x' click
                        f.setTitle("Maze Generator"); //Set the title of the window
                        f.setResizable(false); //Disable resizing
                        f.add(new MazeGeneratorResolver(24), BorderLayout.CENTER); //Add the maze resolver to the frame
                        f.pack(); //Resize the window to fit the maze
                        f.setLocationRelativeTo(null); //Centers the window
                        f.setVisible(true); //Make the window visible
                    });
                    case 5 -> {
                        System.out.println("you choose to leave");
                        System.exit(0); //If user selects 5, exit the program
                    }
                    default -> {
                    }
                }
            }
            while (userSelected > 6 || userSelected<1 ); // Loop until user selects 5
    }
}