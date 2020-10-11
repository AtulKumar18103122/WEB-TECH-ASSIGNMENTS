package com.company;
import java.util.*;
import java.util.Scanner;
import java.lang.String;

class Main {
    public static void main(String[] args)
    {
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        in.nextLine();
        //String[] crops = new String[n];
        StringBuilder[] crops = new StringBuilder[n];
        for(int i = 0; i<n; ++i)
            crops[i] = new StringBuilder();
        for (int i=0;i<n;i++){
            crops[i].append(in.nextLine().trim());
        }

        System.out.print(replant(crops));
    }
    public static int replant(StringBuilder[] crops){
        // Write your code here
        // Return the number of replanted crops
        int n = crops.length;
        int m = crops[0].length();
        int ans = helper(crops, 0, 0);
        return ans;
    }

    private static int helper(StringBuilder[] crops, int x, int y) {
        if(x>=crops.length || y>=crops[0].length())
            return 0;

        if('*' == crops[x].charAt(y)){
            int cost = helper(crops, x+1,y) + helper(crops, x, y+1);
            return cost;
        }

        if((x+1 < crops.length && crops[x].charAt(y) == crops[x+1].charAt(y)) && (y+1 < crops[0].length() && crops[x].charAt(y) == crops[x].charAt(y+1)) )
        {
            crops[x].setCharAt(y,'*');
            int cost = 1 + helper(crops, x+1,y) + helper(crops, x, y+1);
            char a = crops[x+1].charAt(y);
            char b = crops[x].charAt(y+1);
            crops[x+1].setCharAt(y,'*');
            crops[x].setCharAt(y+1,'*');
            int cost2 = 2 + helper(crops, x+1,y) + helper(crops, x, y+1);
            crops[x+1].setCharAt(y,a);
            crops[x].setCharAt(y+1,b);
            return Math.min(cost2, cost);
        }
        if(x+1< crops.length && crops[x].charAt(y) == crops[x+1].charAt(y)){
            crops[x].setCharAt(y,'*');
            char a = crops[x+1].charAt(y);
            crops[x+1].setCharAt(y,'*');
            int ans = 1 + helper(crops, x+1,y) + helper(crops, x, y+1);
            crops[x+1].setCharAt(y,a);
            return ans;
        }
        if(y+1< crops[0].length() && crops[x].charAt(y) == crops[x].charAt(y+1)){
            crops[x].setCharAt(y,'*');
            char a = crops[x].charAt(y+1);
            crops[x].setCharAt(y+1,'*');
            int ans = 1 + helper(crops, x+1,y) + helper(crops, x, y+1);
            crops[x].setCharAt(y+1,a);
            return ans;
        }
        crops[x].setCharAt(y,'*');
        return helper(crops, x+1,y) + helper(crops, x, y+1);
    }
}

