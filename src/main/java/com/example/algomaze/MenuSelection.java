package com.example.algomaze;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MenuSelection {

    //Data of our menu :
    public static int MenuData() {
        int selection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your difficulty");
        System.out.println("--------------------");
        System.out.println("1 - Labyrinth easy ♥");
        System.out.println("2 - Labyrinth medium ★");
        System.out.println("3 - Labyrinth hard ☠");
        System.out.println("4 - Animated labyrinth solved ");
        System.out.println("5 - Leave");
        selection = sc.nextInt();
        return selection;
    }
// menu to add while we display our labyrinth
    public static void menuFinish() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Did you finish ?");
        System.out.println("---------------------");
        System.out.println("1 - quit or (RAGE QUIT)");
        System.out.println("2 - SOLVED ?!");
        int select = sc.nextInt();
        if (select == 1) { //typed 1 exit the game
            System.exit(0);
        } else if (select == 2) { //typed 2 show a solved version
            InputStream n = new FileInputStream("Waze.txt");
            String[] lines = MazeSolver.readLines(n);
            char[][] maze2 = MazeSolver.decimateHorizontally(lines);
            MazeSolver.solveMaze(maze2);
            String[] solvedLines = MazeSolver.expandHorizontally(maze2);
            for (String solvedLine : solvedLines) System.out.println(solvedLine);
        }
    }
        public static void time(){ //time to solve the maze
            Scanner sc = new Scanner(System.in);
            int timeline = 0;
            long start = System.currentTimeMillis(); //start time
            while (true) {
                try {
                    Thread.sleep(1000); //wait 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long elapsedTime = System.currentTimeMillis() - start; // time elapsed
                elapsedTime = elapsedTime / 1000; // convert to seconds
                String seconds = Integer.toString((int) (elapsedTime % 60)); // convert to seconds
                String minutes = Integer.toString((int) ((elapsedTime % 3600) / 60)); // convert to minutes
                String hours = Integer.toString((int) (elapsedTime / 3600)); // convert to hours
                if (seconds.length() < 2) { // add a 0 if the number of seconds is less than 2
                    seconds = "0" + seconds;
                }
                if (minutes.length() < 2) { // add a 0 if the number of minutes is less than 2
                    minutes = "0" + minutes;
                }
                if (hours.length() < 2) { // add a 0 if the number of hours is less than 2
                    hours = "0" + hours;
                }
                String writeThis = hours + ":" + minutes + ":" + seconds;
                for (int i = 0; i < timeline; i++) { // print the time
                    System.out.print("\b");
                }
                System.out.print(writeThis); // print the time
                timeline = writeThis.length(); // update the timeline
                int tiebreak = sc.nextInt();
                if(tiebreak == 1){ // if the user type 1 the game will stop
                    break;
                }
            }
    }
}


