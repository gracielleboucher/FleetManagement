import java.io.Serializable;

/**
 * One boat in the Coconut Grove Sailing Club fleet. A boat knows its details and how much
 * has been spent maintaining it, and applies the Commodore's policy that no more may be spent on a boat
 * than it cost.
 *
 * @author Gracielle Boucher
 * @version 1.0 November 2026
 */
public class Boat implements Serializable {

    /** Whether the boat is a sailing or power boat. */
    private final BoatType type;

    /** The name of the boat. */
    private final String name;

    /** The year the boat was made. */
    private final int year;

    /** The make/model of the boat. */
    private final String makeModel;

    /** The length of the boat in feet, up to 100. */
    private final int length;

    /** The price the club paid for the boat. */
    private final double purchasePrice;

    /** The total spent maintaining the boat. */
    private double expenses;

    /** Creates a boat with default values. */
    public Boat() {
        this(BoatType.SAILING, "", 0, "", 0, 0.0);
    } //end of the default Boat() constructor

    /**
     * Creates a boat with the given details and no expenses yet.
     *
     * @param type whether the boat is a sailing or power boat
     * @param name the name of the boat
     * @param year the year the boat was made
     * @param makeModel the make/model of the boat
     * @param length the length of the boat in feet
     * @param purchasePrice the price the club paid for the boat
     */
    public Boat(BoatType type, String name, int year, String makeModel, int length, double purchasePrice) {
        this.type = type;
        this.name = name;
        this.year = year;
        this.makeModel = makeModel;
        this.length = length;
        this.purchasePrice = purchasePrice;
        this.expenses = 0.0;
    } //end of the Boat() w/ given details constructor

    /**
     * Gets the name of the boat.
     *
     * @return the name of the boat
     */
    public String getName() {
        return name;
    } //end of getName

    /**
     * Gets the price the club paid for the boat.
     *
     * @return the purchase price
     */
    public double getPurchasePrice() {
        return purchasePrice;
    } //end of getPurchasePrice

    /**
     * Gets the total spent maintaining the boat.
     *
     * @return the total expenses
     */
    public double getExpenses() {
        return expenses;
    } //end of getExpenses

    /**
     * Checks whether an amount can be spent on the boat under the Commodore's policy
     * that no more may be spent than the boat cost.
     *
     * @param amount the amount the Commodore wants to spend
     * @return true if the expense is allowed, false if not
     */
    public boolean canSpend(double amount) {
        return expenses + amount <= purchasePrice;
    } //end of canSpend

    /**
     * Adds an approved amount to the boat's expenses.
     *
     * @param amount the amount spent
     */
    public void spend(double amount) {
        expenses += amount;
    } //end of spend

    /**
     * Describes the boat as one line of the fleet report.
     *
     * @return the boat's type, name, year, make/model, length, purchase price, and expenses
     */
    public String toString() {
        return String.format("    %-7s %-20s %4d %-10s %3d' : Paid $%9.2f : Spent $%9.2f", type,
                name, year, makeModel, length, purchasePrice, expenses);
    } //end of toString


} //end of the Boat class
