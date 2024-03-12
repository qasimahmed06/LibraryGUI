package LibraryGUI;

public class User
{
    int userID;
    String name;
    String contactInfo;
    Book[] borrowedBooks;

    // Constructor
    public User(int userID, String name, String contactInfo, int maxBorrowed)
    {
        this.userID = userID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new Book[maxBorrowed];
    }

    // Setters
    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setContactInfo(String contactInfo)
    {
        this.contactInfo = contactInfo;
    }

    // Getters
    public int getUserID()
    {
        return userID;
    }
    public String getName()
    {
        return name;
    }
    public String getContactInfo()
    {
        return contactInfo;
    }
    public Book[] getBorrowedBooks()
    {
        return borrowedBooks;
    }


    // Method to add a borrowed book
    public void addBorrowedBook(Book book) {
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] == null) {
                borrowedBooks[i] = book;
                System.out.println("Book '" + book.getTitle() + "' borrowed successfully.");
                return; // Exit loop after adding the book
            }
        }
        System.out.println("Cannot borrow more books. Maximum limit reached.");
    }
}
