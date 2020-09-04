package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int value = input.nextInt();
        int newValue = 0;
        while (value != 1) {
            if (value % 2 == 0){
                newValue = value / 2;
                System.out.println(value + " is even so I take half: " + newValue);
            }
            else {
                newValue = value * 3 + 1;
                System.out.println(value + " is odd, so I make 3n + 1: " + newValue);
            }
            value = newValue;
        }
    }
}