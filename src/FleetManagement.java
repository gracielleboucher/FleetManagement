import java.util.Scanner;

/**
 * The Fleet Management System for the Coconut Grove Sailing Club.
 * Loads the fleet, lets the Commodore print, add, remove, and spend money on boats, and saves the
 * fleet on exit.
 *
 * @author Gracielle Boucher
 * @version 1.0 November 2026
 */
public class FleetManagement {

    /** The keyboard Scanner. */
    private static final Scanner keyboard = new Scanner(System.in);

    /**
     * Runs the Fleet Management System.
     *
     * @param args the name of the CSV file on the first run, or nothing on later runs
     */
    public static void main(String[] args) {

        String option;

        //Welcome message
        System.out.println("Welcome to the Fleet Management System");
        System.out.println("--------------------------------------");
        System.out.println();

        //Menu loop, repeats until the Commodore chooses X
        do {
            System.out.print("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            option = keyboard.nextLine().toUpperCase();

            switch (option) {
                case "P":
                    System.out.println("Print chosen");
                    System.out.println();
                    break;
                case "A":
                    System.out.println("Add chosen");
                    System.out.println();
                    break;
                case "R":
                    System.out.println("Remove chosen");
                    System.out.println();
                    break;
                case "E":
                    System.out.println("Expense chosen");
                    System.out.println();
                    break;
                case "X":
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid menu option, try again");
                    break;
            }
        } while (!option.equals("X"));

        //Exit message
        System.out.println("Exiting the Fleet Management System");
    } //end of the main method

} //end of the FleetManagement class
