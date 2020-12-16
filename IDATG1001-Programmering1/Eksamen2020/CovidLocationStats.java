import java.time.LocalDate;

/**
 * This class is used to register a countrys covid-19 status a day. It registers the date,
 * the country and how many new people are registered dead and infected that day.
 *
 * @author 10023
 * @version 1.0.0
 */
public class CovidLocationStats {

    /**
     * Fields
     */
    //The field for date
    private LocalDate date;
    //The field for country
    private String country;
    //The field for number of people infected that date
    private int numberOfInfected;
    //The field for number of people dead that date
    private int numberOfDead;

    /**
     * Constructor with 6 parameters
     * @param year Parameter for year
     * @param month Parameter for month
     * @param dayOfMonth Parameter for day of month
     * @param country Parameter for country
     * @param numberOfInfected Parameter for number of people infected that date
     * @param numberOfDead Parameter for number of people dead that date
     */
    public CovidLocationStats(int year, int month, int dayOfMonth, String country, int numberOfInfected, int numberOfDead) {
        this.date = LocalDate.of(year, month, dayOfMonth);
        this.country = country;
        this.numberOfInfected = numberOfInfected;
        this.numberOfDead = numberOfDead;
    }

    /**
     * Accessor method for date
     * @return LocalDate date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Accessor method for country
     * @return string country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Accessor method for number of people infected
     * @return int number of people infected that day
     */
    public int getNumberOfInfected() {
        return numberOfInfected;
    }

    /**
     * Accessor method for number of people dead
     * @return int number of people dead that day
     */
    public int getNumberOfDead() {
        return numberOfDead;
    }

    /**
     * There is no need for mutator methods.
     */

    /**
     * This is a toString method used to obtain string information about an event.
     * @return string information about the object
     */
    @Override
    public String toString() {
        return "Date: " + date +
                " Country: " + country +
                "  Number of infected: " + numberOfInfected +
                "  Number of dead: " + numberOfDead;
    }


}
