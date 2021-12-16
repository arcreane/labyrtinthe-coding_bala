package com.example.algomaze;
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
        System.out.println("5 - Solved labyrinth in terminal");
        System.out.println("6- Leave");
        System.out.println("Your difficulty is: ");
        selection = sc.nextInt();
        return selection;
    }

    public static void menuFinish() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Did you finish ?");
        System.out.println("---------------------");
        System.out.println("1 - quit or (RAGE QUIT)");
        int select = sc.nextInt();
        if (select == 1) {
            System.exit(0);
        }
    }
}


