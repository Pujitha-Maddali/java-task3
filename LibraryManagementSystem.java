import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        this.isIssued = true;
    }

    public void returnBook() {
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
    }
}

class User {
    private String name;
    private int userId;

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}

public class LibraryManagementSystem {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println("\nBooks in Library:");
        for (Book book : books) {
            System.out.println("- " + book);
        }
    }

    public void issueBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isIssued()) {
                book.issueBook();
                System.out.println("✅ Book issued successfully!");
                return;
            }
        }
        System.out.println("❌ Book not available or already issued.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isIssued()) {
                book.returnBook();
                System.out.println("✅ Book returned successfully!");
                return;
            }
        }
        System.out.println("❌ Book not found or already returned.");
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Add sample books
        library.addBook(new Book("Java Basics", "John Doe"));
        library.addBook(new Book("OOP Concepts", "Jane Smith"));
        library.addBook(new Book("Data Structures", "Mark Twain"));

        System.out.println("📚 Welcome to Library Management System");

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("⚠️ Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter book title to issue: ");
                    String titleIssue = scanner.nextLine();
                    library.issueBook(titleIssue);
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    String titleReturn = scanner.nextLine();
                    library.returnBook(titleReturn);
                    break;
                case 4:
                    System.out.println("👋 Exiting... Thank you for using the system!");
                    scanner.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Please choose between 1-4.");
            }
        }
    }
}
