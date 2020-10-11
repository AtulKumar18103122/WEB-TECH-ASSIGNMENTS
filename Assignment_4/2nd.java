package com.company;
class Main {
    public static void main(String[] args) {
        int a = -1;
        byte b = (byte) (a);
        char c = (char) (b);
        int d = (int) (c);
        System.out.println(d);
        /*the value end up back where it started?*/
        /*No, because The cast from byte to char is trickier because byte is a signed type and char unsigned.
          while converting -1 to unsigned char resulting value has all 16 bits set, so it is equal to 65,535.
          then again converting into int make it 65,535.
         */
    }
}

