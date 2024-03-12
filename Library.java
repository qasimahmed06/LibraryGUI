package LibraryGUI;

import java.util.Arrays;

public class Library
{
    Book[] books;
    User[] users;
    int numBooks;
    int numUsers;
    int maxBooks;
    int maxUsers;

    // Constructor
    public Library(int maxBooks, int maxUsers)
    {
        this.maxBooks = maxBooks;
        this.maxUsers = maxUsers;
        this.books = new Book[maxBooks];
        this.users = new User[maxUsers];
        this.numBooks = 0;
        this.numUsers = 0;
    }

    // Getters
    public int getNumBooks()
    {
        return numBooks;
    }

    public int getNumUsers()
    {
        return numUsers;
    }

    // Method to add a new book
    public void addBook(Book book)
    {
        if (numBooks < maxBooks)
        {
            books[numBooks++] = book;
            System.out.println("Book added successfully.");
        }
        else
        {
            System.out.println("Cannot add more books. Library is full.");
        }
    }

    // Method to add a new user
    public void addUser(User user)
    {
        if (numUsers < maxUsers)
        {
            users[numUsers++] = user;
            System.out.println("User added successfully.");
        }
        else
        {
            System.out.println("Cannot add more users. Library is full.");
        }
    }

    // Method to check out a book
    public void checkOutBook(Book book, User user)
    {
        if (numBooks == 0 || numUsers == 0)
        {
            System.out.println("No books or users in the library.");
            return;
        }
        if (book.isAvailable())
        {
            book.setAvailable(false);
            user.addBorrowedBook(book);
            System.out.println("Book checked out successfully.");
        }
        else
        {
            System.out.println("Book is not available.");
        }
    }

    // Method to return a book
    public void returnBook(Book book, User user)
    {
        if (numBooks == 0 || numUsers == 0)
        {
            System.out.println("No books or users in the library.");
            return;
        }
        if (Arrays.asList(user.getBorrowedBooks()).contains(book))
        {
            book.setAvailable(true);
            Book[] borrowedBooks = user.getBorrowedBooks();
            for (int i = 0; i < borrowedBooks.length; i++)
            {
                if (borrowedBooks[i] == book) {
                    borrowedBooks[i] = null;
                    break;
                }
            }
            System.out.println("Book returned successfully.");
        }
        else
        {
            System.out.println("User has not borrowed this book.");
        }
    }


    // Method to search for a book by title
    public void searchByTitle(String title)
    {
        boolean found = false;
        for (int i = 0; i < numBooks; i++)
        {
            if (books[i].getTitle().equalsIgnoreCase(title))
            {
                System.out.println("Book found:\n" + books[i]);
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("Book not found.");
        }
    }

    // Method to search for a book by author
    public void searchByAuthor(String author)
    {
        boolean found = false;
        for (int i = 0; i < numBooks; i++)
        {
            if (books[i].getAuthor().equalsIgnoreCase(author))
            {
                System.out.println("Book found:\n" + books[i]);
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("Book not found.");
        }
    }
}