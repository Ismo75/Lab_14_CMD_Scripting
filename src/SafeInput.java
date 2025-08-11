import java.util.Scanner;

public class SafeInput {

    // Part A
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    // Part B
    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        boolean done = false;

        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                done = true;
            } else {
                String trash = pipe.nextLine();
                System.out.println("You must enter a valid integer. Not: " + trash);
            }
        } while (!done);
        pipe.nextLine(); // clear newline
        return retVal;
    }

    // Part C
    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0;
        boolean done = false;

        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                done = true;
            } else {
                String trash = pipe.nextLine();
                System.out.println("You must enter a valid number. Not: " + trash);
            }
        } while (!done);
        pipe.nextLine(); // clear newline
        return retVal;
    }

    // Part D
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        boolean done = false;

        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Input is out of range.");
                }
            } else {
                String trash = pipe.nextLine();
                System.out.println("You must enter a valid integer. Not: " + trash);
            }
        } while (!done);
        pipe.nextLine(); // clear newline
        return retVal;
    }

    // Part E
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retVal = 0;
        boolean done = false;

        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Input is out of range.");
                }
            } else {
                String trash = pipe.nextLine();
                System.out.println("You must enter a valid number. Not: " + trash);
            }
        } while (!done);
        pipe.nextLine(); // clear newline
        return retVal;
    }

    // Part F
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response;
        boolean valid = false;

        do {
            System.out.print(prompt + " [Y/N]: ");
            response = pipe.nextLine().trim().toUpperCase();

            if (response.equals("Y") || response.equals("N")) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (!valid);

        return response.equals("Y");
    }

    // Part G
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response;
        boolean valid = false;

        do {
            System.out.print(prompt + ": ");
            response = pipe.nextLine();

            if (response.matches(regEx)) {
                valid = true;
            } else {
                System.out.println("Input does not match the required pattern. Try again.");
            }
        } while (!valid);

        return response;
    }

    // Part H
    public static void prettyHeader(String msg) {
        int totalWidth = 60;
        int stars = 3;
        int spaceAround = (totalWidth - stars * 2 - msg.length()) / 2;

        // Top line
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Middle line
        System.out.print("***");
        for (int i = 0; i < spaceAround; i++) System.out.print(" ");
        System.out.print(msg);
        for (int i = 0; i < (totalWidth - stars * 2 - msg.length() - spaceAround); i++) System.out.print(" ");
        System.out.println("***");

        // Bottom line
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
