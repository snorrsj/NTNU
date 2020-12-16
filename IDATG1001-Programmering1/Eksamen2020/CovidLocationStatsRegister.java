import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is a register used to store days with covid-19 data.
 *
 * @author 10023
 * @version 1.0.0
 */
public class CovidLocationStatsRegister {

    /**
     * Field with array list. I have chosen to use array list because it offers all the needed functions and is easily
     * adjustable and iterable.
     */
    private ArrayList<CovidLocationStats> covidLocationStatsArrayList;

    /**
     * Constructor creating the array list
     */
    public CovidLocationStatsRegister() {
        this.covidLocationStatsArrayList = new ArrayList<>();
    }

    /**
     * Method to add a registration of covid-19 , it takes 6 parameters
     * @param year Parameter for year
     * @param month Parameter for month
     * @param dayOfMonth Parameter for day of month
     * @param country Parameter for country
     * @param numberOfInfected Parameter for number of people infected that date
     * @param numberOfDead Parameter for number of people dead that date
     */
    public void addRegistration(int year, int month, int dayOfMonth, String country, int numberOfInfected,
                                int numberOfDead)
    {
        covidLocationStatsArrayList.add(new CovidLocationStats(year, month, dayOfMonth, country, numberOfInfected,
                numberOfDead));
    }

    /**
     * This is a method that searches for a registry by date. it returns the first registered object found. It takes 3
     * parameters and returns a CovidLocationStats object. It stops searching after having found a match.
     * @param year Parameter for year
     * @param month Parameter for month
     * @param dayOfMonth Parameter for day of month
     * @return CovidLocationStats object
     */
    public CovidLocationStats searchDate(int year, int month, int dayOfMonth)
    {
        CovidLocationStats obtainedDate = null;
        Iterator<CovidLocationStats> it = covidLocationStatsArrayList.iterator();
        int index = 0;
        boolean searching = true;
        while(it.hasNext() && searching)
        {
            if (covidLocationStatsArrayList.get(index).getDate() == LocalDate.of(year, month, dayOfMonth))
            {
                obtainedDate = covidLocationStatsArrayList.get(index);
                searching = false;
            }
        }
        return obtainedDate;
    }

    /**
     * This is a method that searches for all events registered after a given date and returns an array containing all
     * objects matching that search.
     * @param year Parameter for year
     * @param month Parameter for month
     * @param dayOfMonth Parameter for day of month
     * @return ArrayList containing all registered events after given date
     */
    public ArrayList<CovidLocationStats> searchAllRegisteredEventsAfterDate(int year, int month, int dayOfMonth)
    {
        ArrayList<CovidLocationStats> registeredEventsAfterDateArray = new ArrayList<>();
        for (int index = 0; index<covidLocationStatsArrayList.size(); index ++)
        {
            if (LocalDate.of(year, month, dayOfMonth).isAfter(covidLocationStatsArrayList.get(index).getDate()))
            {
                registeredEventsAfterDateArray.add(covidLocationStatsArrayList.get(index));
            }
        }
        return registeredEventsAfterDateArray;
    }

    /**
     * This is a method that adds all dead registered in a country together and returns the sum.
     * @param country Parameter for country
     * @return int value of dead combined for all registered events for that country
     */
    public int numberOfDeadInCountry(String country)
    {
        int numberOfDead = 0;
        for (int index = 0; index<covidLocationStatsArrayList.size(); index ++)
        {
            if (covidLocationStatsArrayList.get(index).getCountry().equalsIgnoreCase(country))
            {
                numberOfDead += covidLocationStatsArrayList.get(index).getNumberOfDead();
            }
        }
        return numberOfDead;
    }

    /**
     * This is a method that returns an iterator that can be used to search through this register.
     * @return Iterator
     */
    public Iterator<CovidLocationStats> covidLocationStatsIterator()
    {
        return covidLocationStatsArrayList.iterator();
    }

    /**
     * This is a method that returns how many registered events there are in the register.
     * @return int value of how many registered events there are in the register.
     */
    public int numberOfRegistrationsInRegister()
    {
        return covidLocationStatsArrayList.size();
    }

    /**
     * Accessor method that returns the register
     * @return Covid Locations stats array list
     */
    public ArrayList<CovidLocationStats> getCovidLocationStatsArrayList() {
        return covidLocationStatsArrayList;
    }
}
