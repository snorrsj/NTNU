import LibraryPackage.Book;
import LibraryPackage.BookLibrary;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class is used to run a book library with a text-based user interface
 *
 * @author Snorre Sjaatil
 * @version 1.6.0
 */

public class BookLibraryApp {

    /**
     * This stores the fields book library, 3 scanners and a boolean exit variable
     */
    private BookLibrary bookLibrary;
    private Scanner intScan;
    private Scanner longScan;
    private Scanner stringScan;
    private boolean exit;

    /**
     * This constructor creates a book library, 3 scanners and sets a negative boolean variable
     */
    public BookLibraryApp (){
        this.bookLibrary = new BookLibrary();
        intScan = new Scanner(System.in);
        longScan = new Scanner(System.in);
        stringScan = new Scanner(System.in);
        exit = false;
    }

    /**
     * This method initializes and starts the book library
     */
    private void init(){
        this.bookLibrary.addBooksToLibrary();
        System.out.println("\n-----------------------\n");
        System.out.println("       LIBRARY\n");
        System.out.println("-----------------------");
        listOfCommands();
        inputMain();
    }

    /**
     * This method lists the possible commands in the main menu
     */
    private void listOfCommands()
    {
        System.out.println("\nList of commands:");
        System.out.println("0: Get list of commands");
        System.out.println("1: List all books in library");
        System.out.println("2: Search for author");
        System.out.println("3: Search for book title");
        System.out.println("4: Access a book by index number");
        System.out.println("5: Add book to library");
        System.out.println("6: Remove book from library");
        System.out.println("7: Search for book EAN");
        System.out.println("9: Exit library");
    }

    /**
     * This method contains the program loop and executes a method call based on a user command
     */
    public void inputMain(){

        int inputNumber;

        while (!exit)
        {
            System.out.print("\nMAIN MENU: Please enter a command: ");
            checkForInt();
            inputNumber = intScan.nextInt();

            switch (inputNumber)
            {
                    case 0:
                        listOfCommands();
                        break;
                    case 1:
                        listAllBooks();
                        break;
                    case 2:
                        searchAuthor();
                        break;
                    case 3:
                        searchBookTitle();
                        break;
                    case 4:
                        accessBook();
                        break;
                    case 5:
                        addBook();
                        break;
                    case 6:
                        removeBook();
                        break;
                    case 7:
                        searchEanUI();
                        break;
                    case 9:
                        exit = true;
                        break;
                    default:
                        System.out.println("No command found.");
            }
        }
    }

    /**
     * This method starts a input scanner and performs a check to see if it is a long or not.
     * This is used to prevent the application from crashing from wrong input type.
     */
    private void checkForLong()
    {
        while (!longScan.hasNextLong())
        {
            System.out.println("Please enter a valid number.");
            longScan.next();
        }
    }

    /**
     * This method starts a input scanner and performs a check to see if it is a int or not.
     * This is used to prevent the application from crashing from wrong input type.
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
     * This method searches the library for a matching EAN number
     */
    private void searchEanUI()
    {
        if (bookLibrary.getBookRegister().isEmpty()){
            System.out.println("\nThere are no books in library");
        }
        else
        {
            System.out.println("\nEnter EAN number:");
            checkForLong();
            long inputEan = longScan.nextLong();

            if (bookLibrary.searchEAN(inputEan) != -1)
            {
                printInfoBook(bookLibrary.searchEAN(inputEan));
            }
            else
            {
                System.out.println("No books matching EAN " + inputEan + " found in library.");
            }
        }
    }

    /**
     * This method searches the library for all books from an author
     */
    private void searchAuthor()
    {
        if (bookLibrary.getBookRegister().isEmpty())
        {
            System.out.println("\nThere are no books in library");
        }
        else{
            ArrayList<Book> authorArray = new ArrayList<>();
            ArrayList<Integer> indexArray = new ArrayList<>();

            int index = 0;
            String authorName = null;

            System.out.println("Enter name of author: ");
            String inputName = stringScan.nextLine();

            for (Book obtainedBook : bookLibrary.getBookRegister())
            {
                if (obtainedBook.getAuthor().equalsIgnoreCase(inputName))
                {
                    authorArray.add(obtainedBook);
                    indexArray.add(index);
                    authorName = obtainedBook.getAuthor();
                }
                index ++;
            }
            if (authorArray.isEmpty())
            {
                System.out.println("\nThere are no books written by \"" + inputName + "\" found in library. Check spelling.");
            }
            else{
                int index2 = 0;
                System.out.println("\nBooks by " + authorName + " in library: \n");
                System.out.println("\033[0;1m  Index            Title               Author         Availability  \033[0;0m");
                for (Book obtainedBook : authorArray)
                {
                    System.out.println(String.format("%3s %20s %20s %20s", indexArray.get(index2), obtainedBook.getTitle(), obtainedBook.getAuthor(), obtainedBook.getLoanedString()));
                    index2++;
                }
            }
        }
    }


    /**
     * This method searches the library for all books matching a title
     */
    public void searchBookTitle()
    {
        if (bookLibrary.getBookRegister().isEmpty()) {
            System.out.println("\nThere are no books in library");
        }
        else {
            ArrayList<Book> bookArray = new ArrayList<>();
            ArrayList<Integer> indexArray = new ArrayList<>();
            int index = 0;

            System.out.println("Enter title of book: ");
            String inputName = stringScan.nextLine();

            for (Book obtainedBook : bookLibrary.getBookRegister())
            {
                if (obtainedBook.getTitle().equalsIgnoreCase(inputName))
                {
                    bookArray.add(obtainedBook);
                    indexArray.add(index);
                }
                index++;
            }

            if (bookArray.isEmpty())
            {
                System.out.println("\nThere are no books matching \"" + inputName + "\" in library.");
            }
            else
            {
                int index2 = 0;
                System.out.println("\nBooks matching \"" + inputName + "\" found in library:\n");
                System.out.println("\033[0;1m  Index            Title               Author         Availability  \033[0;0m");
                for (Book obtainedBook : bookArray)
                {
                    System.out.println(String.format("%3s %20s %20s %20s", indexArray.get(index2), obtainedBook.getTitle(), obtainedBook.getAuthor(), obtainedBook.getLoanedString()));
                    index2++;
                }
            }
        }
    }

    /**
     * This method prints all info on a single book and offers a menu where the user can change loaned status or remove book from library. It takes 1 parameter.
     * @param index This is the parameter for the index of the book
     */
    private void printInfoBook(int index)
    {
        if (index < bookLibrary.getBookRegister().size() && index >= 0)
        {
            System.out.println("\n\n\nINDEX " + index);
            System.out.println(bookLibrary.getBookRegister().get(index).toString());
            if (bookLibrary.getBookRegister().get(index).getLoaned()) {
                System.out.println("Loan status:   Book is loaned and not available");
            }
            else {
                System.out.println("Loan status:   Book is available");
            }
            System.out.println("\nMenu:");
            System.out.println("0: Main menu");
            System.out.println("1: Set loaned status to available");
            System.out.println("2: Set loaned status to loaned and unavailable");
            System.out.println("3: Remove this book from library");
            System.out.println("9: Exit library");

            System.out.print("\nSUB MENU: Please enter a command: ");

            String bookTitle = bookLibrary.getBookRegister().get(index).getTitle();

            checkForInt();
            int inputNumber2 = intScan.nextInt();

            switch (inputNumber2)
            {
                case 0:
                    listOfCommands();
                    break;
                case 1:
                    bookLibrary.getBookRegister().get(index).setLoaned(false);
                    System.out.println("\n\"" + bookTitle + "\" set as available.");
                    break;
                case 2:
                    bookLibrary.getBookRegister().get(index).setLoaned(true);
                    System.out.println("\n\"" + bookTitle + "\" set as loaned and unavailable.");
                    break;
                case 3:
                    bookLibrary.getBookRegister().remove(index);
                    System.out.println("\n\"" + bookTitle + "\" removed from library.");
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("No action found.");
            }
        }
        else {
            System.out.println("No book found at index " + index + ".");
        }
    }

    /**
     * This method accesses a single book from index number and starts the print info method
     */
    public void accessBook()
    {
        if (bookLibrary.getBookRegister().isEmpty())
        {
            System.out.println("There are no books in library.");
        }
        else
        {
            System.out.print("Enter index number of book:");
            checkForInt();
            int inputNumber = intScan.nextInt();
            while (inputNumber < 0)
            {
                System.out.print("Value must be positive, please enter index number of book:");
                checkForInt();
                inputNumber = intScan.nextInt();
            }
            printInfoBook(inputNumber);
        }
    }

    /**
     * This method lists all the books in the book library
     */
    public void listAllBooks()
    {
        if (bookLibrary.getBookRegister().isEmpty())
        {
            System.out.println("There are no books in library.");
        }
        else
        {
            Iterator<Book> iterator = bookLibrary.getIterator();
            int index = 0;

            System.out.println("\nList of all books in library: \n");
            System.out.println("\033[0;1m  Index            Title               Author         Availability  \033[0;0m");
            while (iterator.hasNext())
            {
                Book book = iterator.next();
                System.out.println(String.format("%3s %20s %20s %20s", index, book.getTitle(), book.getAuthor(), book.getLoanedString()));
                index++;
            }
            System.out.println("\nTotal number of books in library: " + index);
        }
    }

    /**
     * This method adds a new book to the book library with information from user. It runs a method call on addSingleBook from BookLibrary.
     */
    private void addBook()
    {
        System.out.println("Enter title:");
        String title = StringUtils.capitalize(stringScan.nextLine());
        System.out.println("Enter author:");
        String author = StringUtils.capitalize(stringScan.nextLine());
        System.out.println("Enter publisher:");
        String publisher = StringUtils.capitalize(stringScan.nextLine());
        System.out.println("Enter year of publication:");
        checkForInt();
        int year = intScan.nextInt();
        while (year > 2021 || year < 1500)
        {
            System.out.println("Year not valid, must be between 1500 and 2021");
            checkForInt();
            year = intScan.nextInt();
        }
        System.out.println("Enter number of pages:");
        checkForInt();
        int pages = intScan.nextInt();
        while (pages < 1)
        {
            System.out.println("Number not valid, must be a positive number");
            checkForInt();
            pages = intScan.nextInt();
        }
        System.out.println("Enter EAN:");
        checkForLong();
        long ean = longScan.nextLong();
        while (Long.toString(ean).length() != 13)
        {
            System.out.println("Number not valid, must be 13 characters long");
            checkForLong();
            ean = longScan.nextLong();
        }

        if (bookLibrary.addSingleBook(title, author, publisher, year, pages, ean))
        {
            System.out.println("\n\"" + title + "\" added to library.");
        }
        else
        {
            System.out.println("\nBook with EAN " + ean + " already registered in library and was not added.");
        }
    }



    /**
     * This method takes user input and runs a method call on removeSingleBook from BookLibrary to remove a book from the library.
     */
    private void removeBook()
    {
        System.out.println("Enter index number of book:");
        checkForInt();
        int index = intScan.nextInt();
        while (index <= 0 || index >= bookLibrary.getBookRegister().size())
        {
            System.out.println("Please enter a valid index number. Number must be an existing index number (0 - " + (bookLibrary.getBookRegister().size() -1)  + ").");
            checkForInt();
            index = intScan.nextInt();
        }
        String obtainedBook = bookLibrary.getBookRegister().get(index).getTitle();

        if (bookLibrary.removeSingleBook(index))
        {
            System.out.println("\n\"" + obtainedBook + "\" removed from library.");
        }
        else{
            System.out.println("\nNo book found at index " + index);
        }
    }


    /**
     * This is the main method used to run the book library
     * @param args
     */
    public static void main(String[] args) {
        BookLibraryApp app = new BookLibraryApp();
        app.init();
    }
}
