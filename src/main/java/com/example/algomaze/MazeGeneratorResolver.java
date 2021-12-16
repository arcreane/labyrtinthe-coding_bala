package com.example.algomaze;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.*;
import javax.swing.*;


//------------------------------SWING SOLVER VERSION--------------------------------//
public class MazeGeneratorResolver extends JPanel { // Resolver for the maze
    enum Dir {                  //group of constants to use it everywhere by calling it
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        final int bit;
        final int dx;
        final int dy;
        Dir opposite;

        // use the static initializer to resolve forward references
        static { // initialise the opposite direction for each direction
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }


        Dir(int bit, int dx, int dy) { // constructor for the directions
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    }

    final int nCols; // number of columns
    final int nRows; // number of rows
    final int cellSize = 25; // size of the cells
    final int margin = 25; // margin around the maze
    final int[][] maze; // the maze
    LinkedList<Integer> solution; // List with nodes for the solution

    public MazeGeneratorResolver(int size) {
        setPreferredSize(new Dimension(650, 650)); // set the size of the panel
        setBackground(Color.white); // set the background color
        nCols = size; // set the number of columns
        nRows = size; // set the number of rows
        maze = new int[nRows][nCols]; // create the maze with the size of the rows and columns
        solution = new LinkedList<>(); // create the solution list
        generateMaze(0, 0);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { // when the mouse is pressed on the panel
                new Thread(() -> { // create a new thread
                    solve(0); // solve the maze
                }).start(); // start the thread
            }
        });
    }

    @Override
    public void paintComponent(Graphics gg) { // paint the panel
        super.paintComponent(gg); // call the superclass paintComponent method
        Graphics2D g = (Graphics2D) gg; // cast the Graphics object to Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); // set the rendering hints for antialiasing

        g.setStroke(new BasicStroke(5)); // set the stroke to 5 pixels
        g.setColor(Color.black); // set the color to black

        // draw maze
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {

                int x = margin + c * cellSize; // set the x coordinate with the margin and the cell size
                int y = margin + r * cellSize; // set the y coordinate with the margin and the cell size

                if ((maze[r][c] & 1) == 0) // N
                    g.drawLine(x, y, x + cellSize, y); // draw the line

                if ((maze[r][c] & 2) == 0) // S
                    g.drawLine(x, y + cellSize, x + cellSize, y + cellSize); // draw the line

                if ((maze[r][c] & 4) == 0) // E
                    g.drawLine(x + cellSize, y, x + cellSize, y + cellSize); // draw the line

                if ((maze[r][c] & 8) == 0) // W
                    g.drawLine(x, y, x, y + cellSize); // draw the line
            }
        }

        // draw pathfinding animation
        int offset = margin + cellSize / 2;

        Path2D path = new Path2D.Float(); // create a new path for the pathfinding animation
        path.moveTo(offset, offset); // move to the starting point

        for (int pos : solution) { // for each position in the solution
            int x = pos % nCols * cellSize + offset; // set the x coordinate with the offset and the cell size
            int y = pos / nCols * cellSize + offset; // set the y coordinate with the offset and the cell size
            path.lineTo(x, y); // draw the line
        }


        g.setColor(Color.orange); // set the color to orange
        g.draw(path); // draw the path

        g.setColor(Color.blue); // set the color to blue
        g.fillOval(offset - 5, offset - 5, 10, 10); // draw the starting point as a circle with a radius of 5 pixels

        g.setColor(Color.green); // set the color to green
        int x = offset + (nCols - 1) * cellSize; // set the x coordinate with the offset and the cell size
        int y = offset + (nRows - 1) * cellSize; // set the y coordinate with the offset and the cell size
        g.fillOval(x - 5, y - 5, 10, 10); // draw the ending point as a circle with a radius of 5 pixels

    }

    void generateMaze(int r, int c) { // generate the maze recursively
        Dir[] dirs = Dir.values(); // get all the directions
        Collections.shuffle(Arrays.asList(dirs)); // shuffle the directions
        for (Dir dir : dirs) { // for each direction
            int nc = c + dir.dx; // set the new column with the current column and the direction
            int nr = r + dir.dy; // set the new row with the current row and the direction
            if (withinBounds(nr, nc) && maze[nr][nc] == 0) { // if the new row and new column are within the bounds and the maze is empty
                maze[r][c] |= dir.bit; // set the current row and current column with the direction
                maze[nr][nc] |= dir.opposite.bit; // set the new row and new column with the opposite direction
                generateMaze(nr, nc); // generate the maze recursively
            }
        }
    }

    boolean withinBounds(int r, int c) { // check if the row and column are within the bounds
        return c >= 0 && c < nCols && r >= 0 && r < nRows;
    }

    boolean solve(int pos) { // solve the maze recursively
        if (pos == nCols * nRows - 1) // if the position is the ending position
            return true; // return true

        int c = pos % nCols; // set the column with the position
        int r = pos / nCols; // set the row with the position

        for (Dir dir : Dir.values()) { // for each direction
            int nc = c + dir.dx; // set the new column with the current column and the direction
            int nr = r + dir.dy; // set the new row with the current row and the direction
            if (withinBounds(nr, nc) && (maze[r][c] & dir.bit) != 0
                    && (maze[nr][nc] & 16) == 0) { // if the new row and new column are within the bounds and the maze is not a wall and the new row and new column is not visited

                int newPos = nr * nCols + nc; // set the new position with the new row and new column

                solution.add(newPos); // add the new position to the solution
                maze[nr][nc] |= 16; // set the new row and new column as visited

                animate(); // animate the pathfinding

                if (solve(newPos)) // if the maze is solved recursively
                    return true; // return true

                animate(); // animate the pathfinding

                solution.removeLast(); // remove the last position from the solution
                maze[nr][nc] &= ~16; // set the new row and new column as not visited
            }
        }

        return false; // return false
    }

    void animate() { // animate the pathfinding
        try {
            Thread.sleep(50L); // sleep for 50 milliseconds
        } catch (InterruptedException ignored) {
        }
        repaint(); // repaint the maze
    }
}