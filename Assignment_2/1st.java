package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String first = input.nextLine();
        System.out.print("Enter second string: ");
        String second = input.nextLine();
        int i = 0;
        while(i<first.length() && i<second.length())
        {
            if(first.charAt(i)<second.charAt(i))
            {
                System.out.println(second+ " is larger than "+ first);
                return;
            }
            else if(first.charAt(i)>second.charAt(i))
            {
                System.out.println(first+ " is larger than "+ second);
                return;
            }
            ++i;
        }
        if(i==first.length() && i== second.length())
        {
            System.out.println("Both the strings are equal");
        }
        else if(i == first.length())
        {
            System.out.println(second+ " is larger than "+ first);
        }
        else
        {
            System.out.println(first+ " is larger than "+ second);
        }
    }
}