package com.example.algomaze;
import java.util.Scanner;

public class MenuSelection {

    //Menu de selection
    public static int MenuData(){
        int selection;
        Scanner sc = new Scanner (System.in);
        System.out.println("Select your difficulty");
        System.out.println("--------------------");
        System.out.println("1 - Labyrinth easy ♥");
        System.out.println("2 - Labyrinth medium ★");
        System.out.println("3 - Labyrinth hard ☠");
        System.out.println("4 - Solved labyrinth ");
        System.out.println("5 - Leave");
        System.out.println("Your difficulty is: ");
        selection = sc.nextInt();
        return selection;
    }
}



