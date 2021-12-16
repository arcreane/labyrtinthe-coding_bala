package com.example.algomaze;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.algomaze.MenuSelection.MenuData;
import static com.example.algomaze.MenuSelection.menuFinish;


public class Main {
    public static void main(String[] args) throws IOException {

            int userSelected;
            //Option selection
            do {
                userSelected = MenuData();
                switch (userSelected) {
                    case 1 -> {
                        System.out.println("Labyrinth easy ♥ selected\nthe game will start :");
                        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 5;
                        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 5;
                        MazeGenerator maze = new MazeGenerator(x, y);
                        maze.display();
                        menuFinish();
                    }
                    case 2 -> {
                        System.out.println("Labyrinth medium ★ selected\nthe game will start :");
                        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 10;
                        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 10;
                        MazeGenerator maze = new MazeGenerator(x, y);
                        maze.display();
                        menuFinish();
                    }
                    case 3 -> {
                        System.out.println("Labyrinth hard ☠ selected\nthe game will start :");
                        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 20;
                        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 20;
                        MazeGenerator maze = new MazeGenerator(x, y);
                        maze.display();
                        menuFinish();
                    }
                    case 4 -> SwingUtilities.invokeLater(() -> {
                        JFrame f = new JFrame();
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        f.setTitle("Maze Generator");
                        f.setResizable(false);
                        f.add(new MazeGeneratorResolver(24), BorderLayout.CENTER);
                        f.pack();
                        f.setLocationRelativeTo(null);
                        f.setVisible(true);
                    });
                    case 5 -> {
                        InputStream n = new FileInputStream("Waze.txt");
                        String[] lines = MazeSolver.readLines (n);
                        char[][] maze2 = MazeSolver.decimateHorizontally (lines);
                        MazeSolver.solveMaze (maze2);
                        String[] solvedLines = MazeSolver.expandHorizontally (maze2);
                        for (String solvedLine : solvedLines) System.out.println(solvedLine);
                    }
                    case 6 -> System.exit(0);
                    default -> {
                    }
                }
            }
            while (userSelected > 6 || userSelected<1 );
    }
}