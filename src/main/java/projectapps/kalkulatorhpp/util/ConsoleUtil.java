package projectapps.kalkulatorhpp.util;

import java.util.Scanner;

//Utility console
public class ConsoleUtil {
    public static final Scanner SC = new Scanner(System.in);

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SC.nextLine().trim();
    }

    public static double readDouble(String prompt) {
        while (true) {
            try {
                String s = readLine(prompt);
                return Double.parseDouble(s);
            } catch (Exception e) {
                System.out.println("Input tidak valid, coba lagi.");
            }
        }
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                String s = readLine(prompt);
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("Input tidak valid, coba lagi.");
            }
        }
    }
}
