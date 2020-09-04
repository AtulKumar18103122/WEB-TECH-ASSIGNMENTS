package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int n=scanner.nextInt();
        scanner.nextLine();
        short[] countingArray=new short[21];
        short[] arr=new short[n];
        for(int i=0;i<n;i++)
        {
            System.out.printf("Enter %dth integer of array from range of 0 to 20: ",i);
            short x=scanner.nextShort();
            if(x<0 || x>20)
            {
                System.out.print("You have entered something wrong! Value must be lie within the range of 0 to 20.\n");
                --i;
                continue;
            }
            countingArray[x]++;
            arr[i]=x;
        }
        System.out.println();
        System.out.print("Sorted array is: ");
        int index = 0;
        for(short i=0;i<21;i++)
        {
            for(int j=0;j<countingArray[i];j++)
            {
                 arr[index++] = i;
                 System.out.printf("%d ",i);
            }
        }
    }
}
