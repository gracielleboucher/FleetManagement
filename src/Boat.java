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


} //end of the Boat class
