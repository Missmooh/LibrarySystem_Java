
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    static class Book {
        private String title;
        private String author;
        private String isbn;
        private boolean isCheckedOut;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.isCheckedOut = false;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getIsbn() {
            return isbn;
        }

        public boolean isCheckedOut() {
            return isCheckedOut;
        }

        public void checkOut() {
            isCheckedOut = true;
        }

        public void returnBook() {
            isCheckedOut = false;
        }
    }

    static class Member {
        private String name;
        private int id;

        public Member(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public void checkOutBook(Book book) {
            if (!book.isCheckedOut()) {
                book.checkOut();
                System.out.println(name + " checked out " + book.getTitle());
            } else {
                System.out.println("Sorry, " + book.getTitle() + " is already checked out.");
            }
        }

        public void returnBook(Book book) {
            if (book.isCheckedOut()) {
                book.returnBook();
                System.out.println(name + " returned " + book.getTitle());
            } else {
                System.out.println("This book wasn't checked out.");
            }
        }
    }

    private List<Book> books;
    private List<Member> members;

    public LibrarySystem() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void showBooks() {
        System.out.println("Available books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + (book.isCheckedOut() ? " (Checked out)" : ""));
        }
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
LibrarySystem library = new LibrarySystem();
        
        // Add sample books
library.addBook(new Book("1984", "George Orwell", "9780451524935"));
library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "9780060935467"));
        
        // Add a sample member
        Member member = new Member("Alice", 101);
        library.addMember(member);

        boolean running = true;
        while (running) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Show Books");
            System.out.println("2. Check Out Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to check out: ");
                    String checkOutIsbn = scanner.nextLine();
                    Book checkOutBook = library.findBookByIsbn(checkOutIsbn);
                    if (checkOutBook != null) {
                        member.checkOutBook(checkOutBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    Book returnBook = library.findBookByIsbn(returnIsbn);
                    if (returnBook != null) {
                        member.returnBook(returnBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}