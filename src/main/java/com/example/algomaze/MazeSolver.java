package com.example.algomaze;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.example.algomaze.MazeGenerator.clearScreen;

public class MazeSolver {
    /**
     * Reads a file into an array of strings, one per line.
     */
    static String[] readLines(InputStream f) throws IOException { // read the file
        BufferedReader r = // read the file into an array of strings
                new BufferedReader(new InputStreamReader(f, StandardCharsets.US_ASCII));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = r.readLine()) != null)
            lines.add(line);
        return lines.toArray(new String[0]);
    }

    static char[][] decimateHorizontally(String[] lines) { // display it on horizontal
        final int width = (lines[0].length() + 1) / 2;
        char[][] c = new char[lines.length][width];
        for (int i = 0; i < lines.length; i++)
            for (int j = 0; j < width; j++)
                c[i][j] = lines[i].charAt(j * 2);
        return c;
    }

    private static boolean solveMazeRecursively(char[][] maze, int x, int y, int d) { // recursive function to solve the maze
        //find a path into the maze
        boolean ok = false;
        for (int i = 0; i < 4 && !ok; i++)
            if (i != d)
                switch (i) {
                    // 0 = up, 1 = right, 2 = down, 3 = left
                    case 0:
                        if (maze[y - 1][x] == ' ')
                            ok = solveMazeRecursively(maze, x, y - 2, 2); // go up
                        break;
                    case 1:
                        if (maze[y][x + 1] == ' ')
                            ok = solveMazeRecursively(maze, x + 2, y, 3); // go right
                        break;
                    case 2:
                        if (maze[y + 1][x] == ' ')
                            ok = solveMazeRecursively(maze, x, y + 2, 0); // go down
                        break;
                    case 3:
                        if (maze[y][x - 1] == ' ')
                            ok = solveMazeRecursively(maze, x - 2, y, 1); // go left
                        break;
                }
        // check for end condition
        if (x == 1 && y == 1)
            ok = true;
        // once we have found a solution, draw it as we unwind the recursion
        if (ok) {
            maze[y][x] = '*';
//            switch (d) {
//                case 0 -> maze[y - 1][x] = '♞';
//                case 1 -> maze[y][x + 1] = '♞';
//                case 2 -> maze[y + 1][x] = '*';
//                case 3 -> maze[y][x - 1] = '*';
//            }
        }
        clearScreen();
        return ok;
    }

    static void solveMaze(char[][] maze) {
        solveMazeRecursively(maze, maze[0].length - 2, maze.length - 2, -1);
    } // solve the maze using a recursive function

    static String[] expandHorizontally(char[][] maze) { // display it on horizontal
        char[] tmp = new char[3];
        String[] lines = new String[maze.length];
        for (int i = 0; i < maze.length; i++) {
            StringBuilder sb = new StringBuilder(maze[i].length * 2);
            for (int j = 0; j < maze[i].length; j++)
                if (j % 2 == 0)
                    sb.append(maze[i][j]);
                else {
                    tmp[0] = tmp[1] = tmp[2] = maze[i][j];
                    if (tmp[1] == '*')
                        tmp[0] = tmp[2] = ' ';
                    sb.append(tmp);
                }
            lines[i] = sb.toString();
        }
        return lines;
    }
}