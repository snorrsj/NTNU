import java.time.LocalDate;
import java.util.Scanner;

/**
 * This is a text based UI that lets a user manipulate and print info about a Covid-19 register.
 *
 * @author 10023
 * @version 1.0.0
 */
public class CovidStatsUI {

    /**
     * Fields, the reason for having 2 scanners is that if one scanner is first used to register int data and then
     * string data inside the same method the app may crash.
     */
    //This is the field for an int scanner
    private Scanner intScan;
    //This is the field for a string scanner
    private Scanner stringScan;
    //This is the field for the Covid-19 register
    private CovidLocationStatsRegister covidLocationStatsRegister;

    private static final String VERSION = "v1.0-SNAPSHOT";



    String[] menuItems
            = {
            "1. Add a COVID-19 entry",
            "2. List all COVID-19 entries in the register",
            "3. Find COVID-19 entry by date",
            "4. Find all entries after date",
            "5. Show the total deaths for a country"
    };

    // Constants defining the different menu options, to be used in the
    // switch-case.
    private static final int ADD_COVID_ENTRY_TO_REGISTER = 1;
    private static final int LIST_ALL_COVID_ENTRIES = 2;
    private static final int FIND_COVID_ENTRY_BY_DATE = 3;
    private static final int FIND_COVID_ENTRY_AFTER_DATE = 4;
    private static final int CALCULATE_TOTAL_DEATHS = 5;
    private static final int EXIT = 6;

    /**
     * Creates an instance of the CovidStatsUI User interface. An instance of
     * the CovidStatsRegister is created. It also creates 2 scanners, one for string inputs and 1 for int inputs.
     */
    public CovidStatsUI() {
        intScan = new Scanner(System.in);
        stringScan = new Scanner(System.in);
        covidLocationStatsRegister = new CovidLocationStatsRegister();
    }

    /**
     * Starts the application by showing the menu and retrieving input from the
     * user. Continues until the user decides to exit the application.
     */
    void start() {

        boolean quit = false;

        while (!quit) {
            int menuSelection = this.getMenuChoice();
            switch (menuSelection) {
                case ADD_COVID_ENTRY_TO_REGISTER:
                    addEntry();
                    break;

                case LIST_ALL_COVID_ENTRIES:
                    listAllEntries();
                    break;

                case FIND_COVID_ENTRY_BY_DATE:
                    findCovidEntryByDate();
                    break;

                case FIND_COVID_ENTRY_AFTER_DATE:
                    findCovidEntriesAfterDate();
                    break;

                case CALCULATE_TOTAL_DEATHS:
                    totalDeaths();
                    break;

                case EXIT:
                    System.out.println("\nThank you for using the COVID-19 stats Application "
                            + VERSION + ". Bye!\n");
                    quit = true;
                    break;

                default:
                    System.out.println(
                            "\nERROR: Please provide a number between 1 and " + this.menuItems.length + "..\n");
            }
        }

    }

    /**
     * Displays the menu to the user, and waits for the users input. The user is
     * expected to input an integer between 1 and the max number of menu items..
     * The method returns the input from the user. If the input from the user is
     * invalid, 0 is returned.
     *
     * @return the menu number (between 1 and max menu item number) provided by
     *         the user.
     */
    private int getMenuChoice() {
        int menuSelection = 0;

        System.out.println("\n**** COVID-19 Stats Tool " + VERSION + " ****\n");
        for (String menuItem : menuItems) {
            System.out.println(menuItem);
        }
        int maxMenuItemNumber = menuItems.length + 1;
        System.out.println(maxMenuItemNumber + ". Exit\n");
        System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");

        Scanner reader = new Scanner(System.in);
        if (reader.hasNextInt()) {
            menuSelection = reader.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuSelection;
    }

    /**
     * This is a method used to check if user input is int.
     */
    private void checkForInt()
    {
        while (!intScan.hasNextInt())
        {
            System.out.println("Please enter a valid number.");
            intScan.next();
        }
    }


    /**
     * This is a method used to collect and check for valid date from user input.
     * It returns an array contaning the date.
     * @return Array containing date
     */
    private int[] checkForDate()
    {
        System.out.println("Please enter year");
        checkForInt();
        int year = intScan.nextInt();
        while (year < 2018 || year > 2020)
        {
            System.out.println("Please enter a valid year.");
            checkForInt();
            year = intScan.nextInt();
        }

        System.out.println("Please enter month");
        checkForInt();
        int month = intScan.nextInt();
        while (month > 12 || month < 1)
        {
            System.out.println("Please enter a valid month");
            checkForInt();
            month = intScan.nextInt();
        }

        System.out.println("Please enter day");
        checkForInt();
        int day = intScan.nextInt();
        while (day > 31 || day < 1)
        {
            System.out.println("Please enter a valid day");
            checkForInt();
            day = intScan.nextInt();
        }
        int[] array = new int[] { year, month, day };
        return array;
    }

    /**
     * This is a method that adds a new entry to the register with data from user
     */
    private void addEntry()
    {
        int[] date = checkForDate();
        int year = date[0];
        int month = date[1];
        int day = date[2];

        System.out.println("Please enter country");
        String country = stringScan.nextLine();

        System.out.println("Please enter number of people infected");
        checkForInt();
        int numberInfected = intScan.nextInt();
        while (numberInfected < 0)
        {
            System.out.println("Please enter a valid positive number or 0.");
            checkForInt();
            numberInfected = intScan.nextInt();
        }

        System.out.println("Please enter number of people dead");
        checkForInt();
        int numberDead = intScan.nextInt();
        while(numberDead < 0)
        {
            System.out.println("Please enter a valid positive number or 0.");
            checkForInt();
            numberDead = intScan.nextInt();
        }

        covidLocationStatsRegister.addRegistration(year, month, day, country, numberInfected, numberDead);
        System.out.println("Event added.");
    }

    /**
     * This is a method that lists all entries in the register
     */
    private void listAllEntries()
    {
        if(!covidLocationStatsRegister.getCovidLocationStatsArrayList().isEmpty())
        {
            System.out.println("List of all covid entries in register: \n");
            for (CovidLocationStats obtainedObject : covidLocationStatsRegister.getCovidLocationStatsArrayList())
            {
                System.out.println(obtainedObject.toString());
            }
        }
        else {
            System.out.println("There are no registered events in register");
        }
    }

    /**
     * This is a method that searches the register for an event matching an input date. It prints the first event found.
     */
    private void findCovidEntryByDate()
    {
        int[] date = checkForDate();
        int year = date[0];
        int month = date[1];
        int day = date[2];

        if(covidLocationStatsRegister.searchDate(year, month, day) != null)
        {
            System.out.println("Match found: \n");
            System.out.println(covidLocationStatsRegister.searchDate(year, month, day).toString());
        }
        else{
            System.out.println("No matching event found.");
        }

    }

    /**
     * This is a method that searches the register for all dates after an input date and prints all matching events.
     */
    private void findCovidEntriesAfterDate()
    {
        int[] date = checkForDate();
        int year = date[0];
        int month = date[1];
        int day = date[2];

        if (!covidLocationStatsRegister.searchAllRegisteredEventsAfterDate(year, month, day).isEmpty())
        {
            System.out.println("List of all matching events: \n");
            for (CovidLocationStats obtainedObject :
                    covidLocationStatsRegister.searchAllRegisteredEventsAfterDate(year, month, day))
            {
                System.out.println(obtainedObject.toString());
            }
        }
        else{
            System.out.println("No matching events found.");
        }

    }

    /**
     * This is a method that prints the total number of dead registered in a country.
     */
    private void totalDeaths()
    {
        System.out.println("Please enter country");
        String country = stringScan.nextLine();
        int numberOfDeadTotal = covidLocationStatsRegister.numberOfDeadInCountry(country);
        System.out.println("Number of dead registered in total in " + country + " is: " + numberOfDeadTotal + ".");
    }

    /**
     * The main start method of the application.
     *
     * @param args Commandline arguments as an array of String
     */
    public static void main(String[] args) {
        CovidStatsUI covidStatsUI = new CovidStatsUI();
        covidStatsUI.start();
    }
}
