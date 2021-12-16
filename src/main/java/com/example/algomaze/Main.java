package com.example.algomaze;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 8;
        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 8;
        MazeAlgo maze = new MazeAlgo(x, y);
        maze.display();


    }
}