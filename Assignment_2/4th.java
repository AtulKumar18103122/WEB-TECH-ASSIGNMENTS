package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 1, sum = 1, i = 1;
        while(n < Integer.MAX_VALUE)
        {
            if(sum == n*n)
            {
                System.out.println("The "+ i + " minimum value of n is: " + n);
                i++;
                System.out.println();
            }
            n++;
            sum += n;
        }
    }
}
