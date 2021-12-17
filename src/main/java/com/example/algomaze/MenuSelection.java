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
        public static void time(){
            Scanner sc = new Scanner(System.in);
            int timeline = 0;
            long start = System.currentTimeMillis();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long elapsedTime = System.currentTimeMillis() - start;
                elapsedTime = elapsedTime / 1000;
                String seconds = Integer.toString((int) (elapsedTime % 60));
                String minutes = Integer.toString((int) ((elapsedTime % 3600) / 60));
                String hours = Integer.toString((int) (elapsedTime / 3600));
                if (seconds.length() < 2) {
                    seconds = "0" + seconds;
                }
                if (minutes.length() < 2) {
                    minutes = "0" + minutes;
                }
                if (hours.length() < 2) {
                    hours = "0" + hours;
                }
                String writeThis = hours + ":" + minutes + ":" + seconds;
                for (int i = 0; i < timeline; i++) {
                    System.out.print("\b");
                }
                System.out.print(writeThis);
                timeline = writeThis.length();
                int tiebreak = sc.nextInt();
                if(tiebreak == 1){
                    break;
                }
            }
    }
}


