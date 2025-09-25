import java.util.*;

// Book Class
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return id + " - " + title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

// User Class
class User {
    int userId;
    String name;
    List<Book> borrowedBooks = new ArrayList<>();

    User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Override
    public String toString() {
        return userId + " - " + name;
    }
}

// Library Class
class Library {
    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.title);
    }

    void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.name);
    }

    void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        if (book.isIssued) {
            System.out.println("Book already issued!");
            return;
        }

        book.isIssued = true;
        user.borrowedBooks.add(book);
        System.out.println(user.name + " issued " + book.title);
    }

    void returnBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null || user == null) {
            System.out.println("Book/User not found!");
            return;
        }
        if (!book.isIssued) {
            System.out.println("This book was not issued!");
            return;
        }

        book.isIssued = false;
        user.borrowedBooks.remove(book);
        System.out.println(user.name + " returned " + book.title);
    }

    void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }

    private User findUser(int id) {
        for (User u : users) {
            if (u.userId == id) return u;
        }
        return null;
    }
}

// Main Class with CLI
public class MiniLibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    lib.addBook(new Book(bookId, title, author));
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    lib.addUser(new User(userId, name));
                    break;

                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = sc.nextInt();
                    lib.issueBook(issueBookId, issueUserId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int returnUserId = sc.nextInt();
                    lib.returnBook(returnBookId, returnUserId);
                    break;

                case 5:
                    lib.showBooks();
                    break;

                case 6:
                    System.out.println("Exiting Library System. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}
