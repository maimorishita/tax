package jp.co.isken.tax.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StdIOSample {
    public static void main(String[] args) {
        System.out.println("Hello!");
        System.out.println("http://www.hyuki.com/");
        try {
            BufferedReader stdReader = new BufferedReader(new InputStreamReader(System.in));
            for (int i = 0; i < 2; i++) {
                System.out.println("INPUT: ");
                String line;
                line = stdReader.readLine();
                System.out.println("OUTPUT: " + line);
            }
            stdReader.close();
            System.out.println("PROGRAM END");
        } catch (Exception e) {
            e.getStackTrace();
            System.exit(-1); // ƒvƒƒOƒ‰ƒ€‚ðI—¹
        }
    }
}