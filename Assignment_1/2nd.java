package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type paragraph: ");
        String paragraph = scanner.nextLine();
        Map<String,String> mapReplace = new HashMap<>();
        mapReplace.put("Hello","H****");
        mapReplace.put("Happy","H****");
        for (Map.Entry<String,String> entry : mapReplace.entrySet())
            paragraph = paragraph.replaceAll(entry.getKey(), entry.getValue());
        System.out.println("\nParagraph after replacement: ");
        System.out.println(paragraph);
    }
}