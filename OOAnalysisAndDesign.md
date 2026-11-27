Analysis

    Domain
        Entities:
            Commodore: the person who runs the program and manages the fleet
            Boat: a sailing boat or power boat owned by the club
            Boat type: whether a boat is a sailing boat or a power boat
            Fleet: all the boats owned by a club
            Expense: money spent maintaining a boat
            Commodore's Policy: the club may not spend more on a boat than it cost to buy 
                                the boat
            DB file: the file FleetData.db that holds the saved fleet between runs
            CSV file: the text file (FleetData.csv) that holds the starting fleet data
            Menu: the list of options offered to the Commodore
        Actors:
            Commodore: the Commodore starts the program, chooses menu options,
                        types in data, and reads the results
    Function points
        Things done by the actors:
            - Start the program for the first time, giving the name of a CSV file
            - Start the program on later runs, with no file name
            - Choose a menu option; upper or lower case
            - Print the boat inventory
            - Add a boat by typing one line in CSV format
            - Remove a boat by name; upper or lower case
            - Request permission to spend money on a boat by name (upper or lower case) 
              & amount
            - Exit the program
        Things done by non-actors:
            - The program loads the fleet from the CSV file on the first run
            - The program loads the fleet from FleetData.db on later runs
            - The program saves the fleet to FleetData.db on exit
            - The program finds a boat by name (upper or lower case)
            - The program reports an invalid menu option
            - The program reports a boat that cannot be found
            - A boat checks whether an expense is allowed under the Commodore's policy
            - A boat adds an approved expense to its total expenses
            - A boat describes itself as one line of the fleet report
            - The program totals the purchase prices and expenses of the whole fleet
    Scenarios
        Walk through typical sequences of events
            - First run: The Commodore starts the program with FleetData.csv as the 
                         command line parameter. The program prints the welcome message,
                         reads each line of the CSV file, and creates a boat for
                         each line with expenses of 0.00. The Commodore enters W and the 
                         program prints "Invalid menu option, try again". The Commodore enters 
                         P and the program prints each boat on one line, followed by the   
                         totals line.
            - Add a boat: The Commodore enters A. The program asks for the new boat's CSV 
                          data. The Commodore enters SAILING,Finesse,1974,Tartan,34,9200.50. 
                          The program creates the boat and adds it to the fleet.
            - Remove a boat: The Commodore enters R and the name Lost Soul. No boat has that
                             name, so the program prints "Cannot find boat Lost Soul". 
                             The Commodore enters R and the name BIG BROTHER. The program
                             finds the boat named Big Brother, ignoring case, and removes it 
                             from the fleet.
            - Expense approved: The Commodore enters e, the name Moon Glow, and the amount 
                                3456.78. The program finds Moon Glow and asks it whether the
                                expense is allowed. Moon Glow checks that 0.00 plus 3456.78 is
                                not more than its purchase price of 5500.00, so it adds 
                                3456.78 to its expenses. The program prints "Expense    
                                authorized, $3456.78 spent." The Commodore then spends 2000 on
                                moon glow, and the program prints "Expense authorized,
                                $5456.78 spent.", which is the boat's total expenses.
            - Expense denied: The Commodore enters e, the name moon Glow, and the amount 250. 
                              Moon Glow checks that 5456.78 plus 250 is more than 5500.00, 
                              so the expense is not allowed. The program prints "Expense not 
                              permitted, only $43.22 left to spend.", which is the purchase
                              price minus the expenses.
            - Boat not found for expense: The Commodore enters e and the name Raptor. No boat
                                          has that name, so the program prints "Cannot find 
                                          boat Raptor".
            - Exit and later run: The Commodore enters x. The program saves the whole fleet
                                  to FleetData.db and prints "Exiting the Fleet Management 
                                  System". The Commodore later starts the program with no
                                  command line parameter. The program loads the fleet from
                                  FleetData.db, and printing shows the same boats and expenses
                                  as when the program last exited. 
        Ensure all entities and functions are identified
            - Every entity in the scenarios (the Commodore, the boats, boat type, the fleet,
              expenses, the policy, the CSV file, the DB file, and the menu)
              appears in the domain.
            - Every action in the scenarios (load, save, print, add, remove, find, check an
              expense, record an expense, total, and report errors) appears in the function 
              points.

Design

    Classes and objects
        Domain entities correspond to classes.
            - Boat becomes the class Boat.
            - Boat type becomes the enum BoatType, with the values SAILING and POWER.
            - The program itself is the class FleetManagement, which contains the main method.
            - The fleet is not a separate class. It is an ArrayList of Boat objects created in
              the main method.
            - The Commodore is not a class, because the Commodore is the user and is outside
              the program.
            - An expense is not a class. It is a double amount that is part of a boat's data.
            - The Commodore's policy is not a class. It is a method of Boat, because the boat
              holds its purchase price and expenses.
            - The CSV file and DB file are not classes. They are handled by file methods in
              FleetManagement.
            - The menu is not a class. It is handled by FleetManagement, which is the only 
              class allowed to have a Scanner.
        Things that appear in the scenarios correspond to objects. 
            - Big Brother, Moon Glow, Osita, Rescue II, and Finesse are objects of the class
              Boat.
            - SAILING and POWER are values of the enum BoatType.
            - The fleet is an object of the Java library class ArrayList.
            - The keyboard is an object of the Java library class Scanner, and only 
              FleetManagement has it.
            - The CSV file is read with a Java library Scanner on a FileInputStream.
            - The DB file is read and written with the Java library classes ObjectInputStream
              and ObjectOutputStream.
    Data of objects and classes
        Determine if object (per item) or class (shared) data
            - In Boat, all of the data is object data, because each boat has its own type, 
              name, year, make/model, length, purchase price, and expenses. No data is shared 
              between boats.
            - In FleetManagement, the keyboard Scanner and the constants DB_FILE_NAME and
              PROMPT_FORMAT are class data. The fleet is a local variable in the main method 
              and is passed to other methods as a parameter, so there are no global variables.
        Determine data types
            - type is a BoatType, so it can only be sailing or power
            - name is a String
            - year is an int, because a year is a whole number
            - makeModel is a String
            - length is an int, because lengths are whole feet up to 100
            - purchasePrice is a double, because it is dollars and cents under a million
            - expenses is a double, because it is dollars and cents
            - keyboard is a Scanner
            - DB_FILE_NAME is a String with the value "FleetData.db"
            - PROMPT_FORMAT is a String with the value "%-44s: "
            - fleet is an ArrayList of Boat
        Determine final or not
            - type, name, year, makeModel, length, and purchasePrice are final, because they 
              never change after a boat is created.
            - expenses is not final, because it increases each time an expense is approved.
            - keyboard, PROMPT_FORMAT, and DB_FILE_NAME are final.
            - fleet is not final, because boats are added to it and removed from it.
        Determine if any should be public 
            - None of the data is public.
        Determine which can be accessed 
            - name can be accessed, so that a boat can be found by name.
            - purchasePrice and expenses can be accessed, for the fleet totals and the expense
              messages.
            - type, year, makeModel, and length are accessed only through toString, when a 
              boat describes itself.
        Determine which can be mutated 
            - Only expenses can be mutated, and only when an expense is approved.
            - The fleet is mutated when a boat is added or removed.
    Methods of objects and classes
        Function points correspond to methods
            - Starting with a CSV file corresponds to loadFromCSV in FleetManagement.
            - Starting with no command line parameter corresponds to loadFromDB in 
              FleetManagement.
            - Choosing a menu option corresponds to the menu loop in the main method.
            - Printing the inventory corresponds to printFleet in FleetManagement.
            - Adding a boat corresponds to addBoat and makeBoat in FleetManagement.
            - Removing a boat corresponds to removeBoat in FleetManagement.
            - Requesting an expense corresponds to requestExpense in FleetManagement.
            - Exiting corresponds to saveToDB in FleetManagement.
            - Finding a boat by name corresponds to findBoat in FleetManagement.
            - Checking the Commodore's policy corresponds to canSpend in Boat.
            - Recording an expense corresponds to spend in Boat.
            - A boat describing itself corresponds to toString in Boat.
            - Totaling the fleet is done inside printFleet, using getPurchasePrice and 
              getExpenses from Boat.
        Input and display methods are common
            - All keyboard input is done in FleetManagement because only the class containing
              the main method may have a Scanner.
            - Boat displays itself with toString.
            - printFleet displays the whole fleet report and the totals line.
            - All other messages are printed by FleetManagement.
        For objects
            Constructors 
                - Boat() is the default constructor. It sets type to SAILING, name to an empty 
                  string, year to 0, makeModel to an empty string, length to 0, purchasePrice
                  to 0.0, and expenses to 0.0.
                - Boat(type, name, year, makeModel, length, purchasePrice) sets the six values
                  it is given, and sets expenses to 0.0 because nothing has been spent on a
                  new boat yet.
            toString
                - toString returns one formatted line of the fleet report (showing the type,
                  name, year, make/model, length in feet, purchase price, and expenses). 
            Determine accessors and mutators 
                - getName is an accessor, so that findBoat can compare names.
                - getPurchasePrice is an accessor, used for the fleet totals.
                - getExpenses is an accessor, used for the fleet totals and the expense 
                  messages.
                - canSpend returns true if expenses plus the amount is not more than 
                  purchasePrice.
                - spend is a mutator. It adds an approved amount to expenses.
            Differentiate between public (accessor and mutator) and private (helper) methods
                - In Boat, both constructors, toString, getName, getPurchasePrice, 
                  getExpenses, spend, and canSpend are public, because FleetManagement uses
                  them. 
                - In FleetManagement, the main method is public.
                - In FleetManagement, the following are private methods because only 
                  FleetManagement uses them:
                    - loadFromCSV reads the CSV file line by line, builds a boat using 
                      makeBoat, returns the fleet, and closes the file.
                    - loadFromDB reads the whole fleet from FleetData.db in one read and 
                      closes the file.
                    - saveToDB writes the whole fleet to FleetData.db in one write and closes
                      the file.
                    - makeBoat splits a CSV line on commas and returns a new Boat
                    - printFleet prints every boat and then the totals line.
                    - addBoat asks for a CSV line and adds the new boat to the fleet.
                    - removeBoat asks for a name and removes that boat, or prints 
                      "Cannot find boat" and the name.
                    - requestExpense asks for a name and an amount, then prints that the 
                      expense is authorized or not permitted.
                    - findBoat returns the boat whose name matches without regard for case,
                      or null if there is none.
