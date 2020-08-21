package com.company;

import java.util.Scanner;

public class Main {
    static boolean isAnagram(String first, String second) {
        int N = first.length();
        int n = second.length();
        if(n != N)
        	return false;
        int[] count = new int[256];
        for (int i = 0; i < n; i++)
            count[first.charAt(i)]++;
        for (int i = 0; i < n; i++) {
            if(count[second.charAt(i)] <= 0)
                return false;
            count[second.charAt(i)]--;
        }
        return true;
    }
    public static void main(String[] args) {
        String first;
        String second;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first string/number: ");
        first = scanner.nextLine();
        System.out.print("Enter second string/number: ");
        second = scanner.nextLine();
        if(isAnagram(first, second))
            System.out.println("Strings/numbers are anagrams");
        else
            System.out.println("Strings/numbers are NOT anagrams");
    }
}
