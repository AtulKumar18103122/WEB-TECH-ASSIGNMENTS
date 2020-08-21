package com.company;

import java.util.Scanner;

public class Main {
    static int countOccurrences(String string, String substring) {
        int N = string.length();
        int n = substring.length();
        if(n > N) 
            return false;
        int[] count = new int[256];
        for (int i = 0; i < n; i++)
            count[substring.charAt(i)]++;
        int no = n;
        for (int i = 0; i < n; i++) {
            if(count[string.charAt(i)]>0)
                no--;
            count[string.charAt(i)]--;
        }
        int res = 0;
        if (no==0)
            res++;

        for (int i = n; i < N; i++) {
            if(count[string.charAt(i - n)] >= 0)
                no++;
            count[string.charAt(i - n)]++;
            if(count[string.charAt(i)]>0)
                no--;
            count[string.charAt(i)]--;
            if (no==0)
                res++;
        }
        return res;
    }
    public static void main(String[] args) {
        String string;
        String substring;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string: ");
        string = scanner.nextLine();
        System.out.print("Enter substring: ");
        substring = scanner.nextLine();
        System.out.print(countOccurrences(string, substring));
    }
}
