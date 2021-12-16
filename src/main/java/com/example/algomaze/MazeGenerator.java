package com.example.algomaze;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Arrays;


/*
 * recursive backtracking algorithm
 */
public class MazeGenerator { // initialise the variable for the size of the maze
    private final int abscissa;
    private final int ordinate;
    private final int[][] maze; // initialise the maze array

    public MazeGenerator(int x, int y) { // constructor of the maze
        this.abscissa = x;
        this.ordinate = y;
        maze = new int[this.abscissa][this.ordinate];
        generateMaze(0, 0);
    }

    public void display() throws IOException { // display the maze
        BufferedWriter writer = new BufferedWriter(new FileWriter("Waze.txt",false));

        for (int i = 0; i < ordinate; i++) {

            // draw the north edge
            for (int j = 0; j < abscissa; j++) {
                System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
                writer.write((maze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            writer.write("+ \n");
            // draw the west edge
            for (int j = 0; j < abscissa; j++) {
                writer.write((maze[j][i] & 8) == 0 ? "|   " : "    ");
                System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            writer.write("|\n");
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < abscissa; j++) {
            System.out.print("+---");
            writer.write("#---");
        }
        System.out.println("+");
        writer.write("+\n");
        writer.flush();
    }


    private void generateMaze(int cx, int cy) { // generate the maze using the recursive backtracking algorithm
        DIR[] dirs = DIR.values(); // initialise the array of directions
        Collections.shuffle(Arrays.asList(dirs)); // shuffle the directions
        for (DIR dir : dirs) { // for each direction
            int nx = cx + dir.dx; // calculate the new x coordinate
            int ny = cy + dir.dy; // calculate the new y coordinate
            if (between(nx, abscissa) && between(ny, ordinate) // if the new coordinates are within the maze
                    && (maze[nx][ny] == 0)) { // and the new coordinates are not visited
                maze[cx][cy] |= dir.bit; // mark the current cell as visited
                maze[nx][ny] |= dir.opposite.bit; // mark the new cell as visited
                generateMaze(nx, ny); // generate the maze from the new cell
            }
        }
    }

    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
    }  // check if the new coordinates are within the maze and not visited (used for the recursive backtracking algorithm)

    private enum DIR { // initializing the directions
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private DIR opposite;

        // use the static initializer to resolve forward references
        static { // initialise the opposite direction for each direction
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }

        DIR(int bit, int dx, int dy) { // constructor of the direction
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    }
}