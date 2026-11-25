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

    /** The format for the prompts. */
    private static final String PROMPT_FORMAT = "%-44s: ";

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
                    addBoat(fleet);
                    System.out.println();
                    break;
                case "R":
                    removeBoat(fleet);
                    System.out.println();
                    break;
                case "E":
                    requestExpense(fleet);
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

    /**
     * Adds a new boat to the fleet from CSV data typed by the Commodore.
     *
     * @param fleet the fleet of boats
     */
    private static void addBoat(ArrayList<Boat> fleet) {

        System.out.printf(PROMPT_FORMAT, "Please enter the new boat CSV data");
        fleet.add(makeBoat(keyboard.nextLine()));
    } //end of addBoat

    /**
     * Removes a boat from the fleet, chosen by name.
     *
     * @param fleet the fleet of boats
     */
    private static void removeBoat(ArrayList<Boat> fleet) {

        String name;
        Boat boat;

        System.out.printf(PROMPT_FORMAT, "Which boat do you want to remove?");
        name = keyboard.nextLine();
        boat = findBoat(fleet, name);
        if (boat == null) {
            System.out.println("Cannot find boat " + name);
        } else {
            fleet.remove(boat);
        }
    } //end of removeBoat

    /**
     * Asks which boat to spend on and how much, and spends it if the boat can afford it.
     *
     * @param fleet the fleet of boats
     */
    private static void requestExpense(ArrayList<Boat> fleet) {

        String name;
        Boat boat;
        double amount;

        System.out.printf(PROMPT_FORMAT, "Which boat do you want to spend on?");
        name = keyboard.nextLine();
        boat = findBoat(fleet, name);
        if (boat == null) {
            System.out.println("Cannot find boat " + name);
        } else {
            System.out.printf(PROMPT_FORMAT, "How much do you want to spend?");
            amount = Double.parseDouble(keyboard.nextLine());
            if (boat.canSpend(amount)) {
                boat.spend(amount);
                System.out.printf("Expense authorized, $%.2f spent.%n", boat.getExpenses());
            } else {
                System.out.printf("Expense not permitted, only $%.2f left to spend.%n",
                        boat.getPurchasePrice() - boat.getExpenses());
            }
        }
    } //end of requestExpense

    /**
     * Finds a boat in the fleet by name.
     *
     * @param fleet the fleet of boats
     * @param name the name of the boat to find
     * @return the boat with that name, or null if it is not in the fleet
     */
    private static Boat findBoat(ArrayList<Boat> fleet, String name) {

        int index;

        for (index = 0; index < fleet.size(); index++) {
            if (fleet.get(index).getName().toUpperCase().equals(name.toUpperCase())) {
                return fleet.get(index);
            }
        }
        return null;
    } //end of findBoat

} //end of the FleetManagement class
