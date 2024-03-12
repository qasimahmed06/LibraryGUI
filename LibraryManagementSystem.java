package LibraryGUI;

import java.awt.*; // For coloured buttons
import java.util.Scanner; // For inputs
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // For inputs in the form of button presses
import java.io.*;

public class LibraryManagementSystem
{
    private static final String BOOKS_FILE = "books.txt";
    private static final String USERS_FILE = "users.txt";
    private static Library library;
    private static int loggedInUserID = -1;

    public static void main(String[] args)
    {
        initializeLibrary();
        // Attempting to log in or sign up
        boolean loggedIn = false;
        while (!loggedIn) {
            int option = Integer.parseInt(JOptionPane.showInputDialog("Choose an option:\n1. Login\n2. Sign up\n3. Exit"));
            switch (option) {
                case 1:
                    loggedIn = login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    System.exit(0);
            }
        }
        SwingUtilities.invokeLater(LibraryManagementSystem::createAndShowGUI);
    }

    private static void initializeLibrary()
    {
        library = new Library(100, 50);
        // Loading books and users from files
        loadBooksFromFile();
        loadUsersFromFile();
    }

    // Updating the login method to set loggedInUserID when a user logs in successfully
    private static boolean login()
    {
        int userID = Integer.parseInt(JOptionPane.showInputDialog("Enter User ID: "));
        for (User user : library.users) {
            if (user != null && user.getUserID() == userID)
            {
                loggedInUserID = userID; // Setting loggedInUserID to the logged-in user's ID
                return true; // User found, login successful
            }
        }
        JOptionPane.showMessageDialog(null, "User not found. Please sign up.");
        return false; // For when the log in process fails
    }

    private static void signup()
    {
        int userID = Integer.parseInt(JOptionPane.showInputDialog("Enter User ID: "));
        String name = JOptionPane.showInputDialog("Enter Name: ");
        String contactInfo = JOptionPane.showInputDialog("Enter Contact Info: ");
        User user = new User(userID, name, contactInfo, 5); // Assuming maximum borrowed books is 5
        library.addUser(user);
        saveUsersToFile();
        JOptionPane.showMessageDialog(null, "Signup successful. You can now log in.");
    }

    private static void loadBooksFromFile()
    {
        try {
            File file = new File(BOOKS_FILE);
            if (!file.exists())
            {
                file.createNewFile();
                System.out.println("Books file created.");
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int bookID = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                String genre = parts[3];
                boolean available = Boolean.parseBoolean(parts[4]);
                Book book = new Book(bookID, title, author, genre, available);
                library.addBook(book);
            }
            scanner.close();
        }
        catch (IOException e)
        {
            System.out.println("Error loading books from file: " + e.getMessage());
        }
    }

    private static void loadUsersFromFile()
    {
        try
        {
            File file = new File(USERS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Users file created.");
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int userID = Integer.parseInt(parts[0]);
                String name = parts[1];
                String contactInfo = parts[2];
                User user = new User(userID, name, contactInfo, 5); // Assuming maximum borrowed books is 5
                library.addUser(user);
            }
            scanner.close();
        }
        catch (IOException e)
        {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }

    // Save books to file
    private static void saveBooksToFile()
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE)))
        {
            for (int i = 0; i < library.numBooks; i++)
            {
                Book book = library.books[i];
                writer.println(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.isAvailable());
            }
        }
        catch (IOException e)
        {
            System.out.println("Error saving books to file.");
        }
    }

    // Save users to file
    private static void saveUsersToFile()
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE)))
        {
            for (int i = 0; i < library.numUsers; i++)
            {
                User user = library.users[i];
                writer.println(user.getUserID() + "," + user.getName() + "," + user.getContactInfo());
            }
        }
        catch (IOException e)
        {
            System.out.println("Error saving users to file.");
        }
    }

    // Create and show the GUI
    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        // Creating the GUI frame

        JButton addBookButton = new JButton("Add Book");
        JButton addUserButton = new JButton("Add User");
        JButton displayBooksButton = new JButton("Display Books");
        JButton checkOutBookButton = new JButton("Check Out Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton searchByUserIdButton = new JButton("Search Books");

        Dimension buttonSize = new Dimension(300, 60); // Setting a default button size

        // Resizing the buttons to the default size
        addBookButton.setPreferredSize(buttonSize);
        addUserButton.setPreferredSize(buttonSize);
        displayBooksButton.setPreferredSize(buttonSize);
        checkOutBookButton.setPreferredSize(buttonSize);
        returnBookButton.setPreferredSize(buttonSize);
        searchByUserIdButton.setPreferredSize(buttonSize);

        // Setting button colours
        addBookButton.setBackground(Color.YELLOW);
        addUserButton.setBackground(Color.YELLOW);
        displayBooksButton.setBackground(Color.CYAN);
        checkOutBookButton.setBackground(Color.GREEN);
        returnBookButton.setBackground(Color.GREEN);
        searchByUserIdButton.setBackground(Color.CYAN);

        // Adding the buttons to the GUI
        panel.add(addBookButton);
        panel.add(addUserButton);
        panel.add(displayBooksButton);
        panel.add(checkOutBookButton);
        panel.add(returnBookButton);
        panel.add(searchByUserIdButton);

        // Taking input from the user in the form of actions
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        displayBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayBooks();
            }
        });

        checkOutBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkOutBook();
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        searchByUserIdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchByUserId();
            }
        });

        // Setting the size of the GUI window
        panel.setLayout(new GridLayout(2, 3, 10, 10));
        frame.pack();
        frame.setMinimumSize(new Dimension(750 , 250));
        frame.setVisible(true);
    }

    // Method to add a new book
    private static void addBook()
    {
        int bookID = Integer.parseInt(JOptionPane.showInputDialog("Enter Book ID: "));
        String title = JOptionPane.showInputDialog("Enter Title: ");
        String author = JOptionPane.showInputDialog("Enter Author: ");
        String genre = JOptionPane.showInputDialog("Enter Genre: ");
        Book book = new Book(bookID, title, author, genre, true);
        library.addBook(book);
        saveBooksToFile();
    }

    // Method to add a new user
    private static void addUser()
    {
        int userID = Integer.parseInt(JOptionPane.showInputDialog("Enter User ID: "));
        String name = JOptionPane.showInputDialog("Enter Name: ");
        String contactInfo = JOptionPane.showInputDialog("Enter Contact Info: ");
        User user = new User(userID, name, contactInfo, 5); // Assuming maximum borrowed books is 5
        library.addUser(user);
        saveUsersToFile();
    }

    // Method to display all books
    private static void displayBooks()
    {
        StringBuilder bookList = new StringBuilder("--- List of Books ---\n");
        for (int i = 0; i < library.numBooks; i++) {
            bookList.append(library.books[i]).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, bookList.toString());
    }

    // Method to check out a book
    private static void checkOutBook()
    {
        int bookID = Integer.parseInt(JOptionPane.showInputDialog("Enter Book ID to check out: "));
        Book book = findBookByID(bookID);
        User user = findUserByID(loggedInUserID); // Use loggedInUserID
        if (book != null && user != null) {
            library.checkOutBook(book, user);
            saveBooksToFile();
            saveUsersToFile();
        } else {
            JOptionPane.showMessageDialog(null, "Book is not available.");
        }
    }

    // Method to return a book
    private static void returnBook()
    {
        int bookID = Integer.parseInt(JOptionPane.showInputDialog("Enter Book ID to return: "));
        Book book = findBookByID(bookID);
        User user = findUserByID(loggedInUserID); // Use loggedInUserID
        if (book != null && user != null)
        {
            library.returnBook(book, user);
            saveBooksToFile();
            saveUsersToFile();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Book or user not found.");
        }
    }

    // Method to search for books by user ID
    private static void searchByUserId()
    {
        int userID = Integer.parseInt(JOptionPane.showInputDialog("Enter User ID: "));
        User user = findUserByID(userID);
        if (user != null)
        {
            StringBuilder bookList = new StringBuilder("--- Books borrowed by User ID " + userID + " ---\n");
            for (Book book : user.getBorrowedBooks())
            {
                if (book != null)
                {
                    bookList.append(book).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, bookList.toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "User not found.");
        }
    }

    // Method to find a book by the book ID
    private static Book findBookByID(int bookID)
    {
        for (int i = 0; i < library.numBooks; i++)
        {
            if (library.books[i].getBookID() == bookID)
            {
                return library.books[i];
            }
        }
        return null;
    }

    // Method to find a user by the user ID
    private static User findUserByID(int userID)
    {
        for (int i = 0; i < library.numUsers; i++)
        {
            if (library.users[i].getUserID() == userID)
            {
                return library.users[i];
            }
        }
        return null;
    }
}