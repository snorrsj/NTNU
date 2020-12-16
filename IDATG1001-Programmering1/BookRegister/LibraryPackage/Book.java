package LibraryPackage;

import java.util.Objects;

/**
 * This class is used to create and manage a book.
 *
 * @author Snorre Sjaatil
 * @version 1.5.0
 */


public class Book {

    /**
     * These are the fields that store information about a book.
     */
    private final String TITLE;
    private final String AUTHOR;
    private final String PUBLISHER;
    private final int YEAR;
    private final int PAGES;
    private final long EAN;
    private boolean loaned;


    /**
     * This is a constructor that takes 7 parameters as described.
     *
     * @param title This is the parameter for book title
     * @param author This is the parameter for book author
     * @param publisher This is the parameter for book publisher
     * @param year This is the parameter for the books year published
     * @param pages This is the parameter for book pages
     * @param ean This is the parameter for book EAN
     * @param loaned This is the parameter for book loaned
     */
    public Book(String title, String author, String publisher, int year, int pages, long ean, boolean loaned)
    {
        this.TITLE = title;
        this.AUTHOR = author;
        this.PUBLISHER = publisher;
        this.YEAR = year;
        this.PAGES = pages;
        this.EAN = ean;
        this.loaned = loaned;
    }

    /**
     * This method returns book title
     * @return Book title
     *
     */
    public String getTitle() {
        return TITLE;
    }

    /**
     * This method returns book author
     * @return Book author
     */
    public String getAuthor() {
        return AUTHOR;
    }

    /**
     * This method returns book publisher
     * @return Book publisher
     */
    public String getPublisher() {
        return PUBLISHER;
    }

    /**
     * This method returns year of publication
     * @return Year published
     */
    public int getYear() {
        return YEAR;
    }

    /**
     * This method returns book page count
     * @return Book page count
     */
    public int getPages() {
        return PAGES;
    }

    /**
     * This method returns book EAN
     * @return Book EAN
     */
    public long getEan() {
        return EAN;
    }

    /**
     * This method returns boolean loaned
     * @return Boolean book loaned status
     */
    public boolean getLoaned(){
        return loaned;
    }


    /**
     * This is a method that returns loaned status in a String
     * @return String loan status
     */
    public String getLoanedString() {
        String loanedString;
        if (this.loaned) {
            loanedString = "Unavailable";
        }
        else{
            loanedString = "Available";
        }
        return loanedString;
    }


    /**
     * This method sets loaned status
     * @param isLoaned This is the parameter for loaned
     */
    public void setLoaned(boolean isLoaned){
        this.loaned = isLoaned;
    }


    /**
     * This function sets what the hash set checks for in duplicate detection
     * @param o The parameter for the object
     * @return Boolean true/false duplicate
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return EAN == book.EAN;
    }

    /**
     * This function returns the hashcode after duplicate detection
     * @return Objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(EAN);
    }

    /**
     * This method returns String information about the book, overwriting the default toString() method
     * @return String
     */
    @Override
    public String toString() {
        return "\nTitle:         " + TITLE +
               "\nAuthor:        " + AUTHOR +
                "\nPublisher:     " + PUBLISHER +
                "\nYear:          " + YEAR +
                "\nPages:         " + PAGES +
                "\nEAN:           " + EAN
                ;
    }
}

