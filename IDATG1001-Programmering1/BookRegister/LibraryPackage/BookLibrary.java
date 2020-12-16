package LibraryPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class is used to create and manage a book library
 *
 * @author: Snorre Sjaatil
 * @version: 1.6.0
 */

public class BookLibrary {

    /**
     * This declares the fields book register and hash set library
     */
    private ArrayList<Book> bookRegister;
    private HashSet<Book> hashSetLibrary;

    /**
     * This constructor creates a book register and a hash set
     */
    public BookLibrary() {
        this.bookRegister = new ArrayList<>();
        this.hashSetLibrary = new HashSet<>();
    }

    /**
     * This is a method that sorts the books in the register by title alphabetically.
     */
    public void sortBookRegister(){
        Collections.sort(this.bookRegister, Comparator.comparing(Book::getTitle) );
    }

    /**
     * This method adds 7 books to the book register.
     */
    public void addBooksToLibrary(){
        this.hashSetLibrary.add(new Book("Min Instastory", "Mads Hansen", "Kagge", 2020, 238, 9788248926184L, false));
        this.hashSetLibrary.add(new Book("Gul Bok", "Zeshan Shakar", "Gyldendal", 2020, 286, 9788205538215L, false));
        this.hashSetLibrary.add(new Book("Kongeriket", "Jo Nesbø", "Aschehoug", 2020, 630, 9788205538228L, false));
        this.hashSetLibrary.add(new Book("Jegerånden", "Erik Kristoffersen", "Gyldendal", 2020, 357, 9788205538293L, false));
        this.hashSetLibrary.add(new Book("Nabovarsel", "Unni Lindell", "Gyldendal", 2020, 587, 97882098538211L, false));
        this.hashSetLibrary.add(new Book("Kniv", "Jo Nesbø", "Aschehoug", 2019, 519, 9788203364181L, false));
        this.hashSetLibrary.add(new Book("Snømannen", "Jo Nesbø", "Aschehoug", 2017, 438, 9788203362446L, false));
        this.bookRegister.addAll(this.hashSetLibrary);
        sortBookRegister();
    }


    /**
     * This method checks for duplicates and adds a new book to the library. It takes 6 parameters and returns boolean true if book was added.
     * @param title This is the parameter for book title
     * @param author This is the parameter for book author
     * @param publisher This is the parameter for book publisher
     * @param year This is the parameter for year of release
     * @param pages This is the parameter for number of pages
     * @param ean This is the parameter for book EAN code
     * @return Boolean true if added, false if duplicate detected
     */
    public boolean addSingleBook(String title, String author, String publisher, int year, int pages, long ean)
    {
        boolean bookAdded = false;
        if (!hashSetLibrary.contains((new Book(title, author, publisher, year, pages, ean, false))))
        {
            hashSetLibrary.add((new Book(title, author, publisher, year, pages, ean, false)));
            bookRegister.add((new Book(title, author, publisher, year, pages, ean, false)));
            sortBookRegister();
            bookAdded = true;
        }
        return bookAdded;
    }


    /**
     * This method removes a book from the library. It takes the parameter of the index of the book.
     * @param index This is the index number of the book in the array list.
     * @return boolean true if book removed, false if no book found
     */
    public boolean removeSingleBook(int index)
    {
        boolean bookRemoved = false;
        if (index >= 0 && index < bookRegister.size()){
            bookRegister.remove(index);
            bookRemoved = true;
        }
        return bookRemoved;
    }


    /**
     * This method returns the size of the library array.
     * @return int value of array size
     */
    public int librarySize()
    {
        return bookRegister.size();
    }

    /**
     * This method searches the library for a matching EAN number and returns the index value of the book
     * @param inputEan This is the parameter for book EAN
     * @return int value of index number
     */
    public int searchEAN(long inputEan)
    {
        int index = 0;
        boolean searching = true;
        Iterator<Book> it = bookRegister.iterator();

        while (it.hasNext() && searching)
        {
            if (it.next().getEan() == inputEan)
            {
                searching = false;
            }
            else{
                index++;
            }
        }
        if(searching){
            index = -1;
        }
        return index;
    }


    /**
     * This is an accessor method that returns an iterator
     * @return iterator
     */
    public Iterator<Book> getIterator(){
        return this.bookRegister.iterator();
    }

    /**
     * This is an accessor method that returns the book register
     * @return Book register
     */
    public ArrayList<Book> getBookRegister() {
        return this.bookRegister;
    }


}
















