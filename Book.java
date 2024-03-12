package LibraryGUI;

public class Book
{
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private boolean availabilityStatus;

    // Constructor
    public Book(int bookID, String title, String author, String genre, boolean availabilityStatus)
    {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    // Setters
    public void setBookID(int bookID)
    {
        this.bookID = bookID;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public void setAvailable(boolean availabilityStatus)
    {
        this.availabilityStatus = availabilityStatus;
    }

    // Getters
    public int getBookID()
    {
        return bookID;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getGenre()
    {
        return genre;
    }

    public boolean isAvailable()
    {
        return availabilityStatus;
    }

    // For displaying book details
    public String toString() {
        return "Book ID: " + bookID + "\nTitle: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nAvailability: " + (availabilityStatus ? "Available" : "Not Available");
    }
}