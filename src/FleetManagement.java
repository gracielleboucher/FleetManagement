import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;

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
        ArrayList<Boat> fleet;

        //Welcome message
        System.out.println("Welcome to the Fleet Management System");
        System.out.println("--------------------------------------");
        System.out.println();

        //Load the fleet from the CSV file
        if (args.length > 0) {
            fleet = loadFromCSV(args[0]);
        } else {
            fleet = new ArrayList<>();
        }

        //Menu loop, repeats until the Commodore chooses X
        do {
            System.out.print("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            option = keyboard.nextLine().toUpperCase();

            switch (option) {
                case "P":
                    printFleet(fleet);
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

    /**
     * Loads the fleet from a CSV file, one boat per line.
     *
     * @param fileName the name of the CSV file
     * @return the fleet of boats read from the file
     */
    private static ArrayList<Boat> loadFromCSV(String fileName) {

        ArrayList<Boat> fleet = new ArrayList<>();
        Scanner csvFile = null;

        try {
            csvFile = new Scanner(new FileInputStream(fileName));
            while (csvFile.hasNextLine()) {
                fleet.add(makeBoat(csvFile.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("Error opening or reading " + fileName);
        } finally {
            if (csvFile != null) {
                csvFile.close();
            }
        }

        return fleet;
    } //end of loadFromCSV

    /**
     * Makes a boat from one line of CSV data.
     *
     * @param csvLine the boat's type, name, year, make/model, length, and purchase price, separated by commas
     * @return the new boat
     */
    private static Boat makeBoat(String csvLine) {

        String[] parts = csvLine.split(",");
        return new Boat(BoatType.valueOf(parts[0]), parts[1], Integer.parseInt(parts[2]), parts[3],
                Integer.parseInt(parts[4]), Double.parseDouble(parts[5]));
    } //end of makeBoat

    /**
     * Prints every boat in the fleet, followed by the total paid and spent.
     *
     * @param fleet the fleet of boats
     */
    private static void printFleet(ArrayList<Boat> fleet) {

        int index;
        double totalPaid = 0.0;
        double totalSpent = 0.0;

        System.out.println();
        System.out.println("Fleet report:");
        for (index = 0; index < fleet.size(); index++) {
            System.out.println(fleet.get(index));
            totalPaid += fleet.get(index).getPurchasePrice();
            totalSpent += fleet.get(index).getExpenses();
        }
        System.out.printf("    %-49s : Paid $%9.2f : Spent $%9.2f%n", "Total", totalPaid, totalSpent);
    } //end of printFleet

} //end of the FleetManagement class
