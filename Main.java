/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;

class Member {
    String memberId;
    String name;
    List<String> borrowedBooks = new ArrayList<>();

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}

class Book {
    String bookId;
    String title;
    boolean isIssued;

    public Book(String bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.isIssued = false;
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Member> members = new HashMap<>();
    static Map<String, Book> books = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Library System ====");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Members");
            System.out.println("6. View Books");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1 -> addMember();
                case 2 -> addBook();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> viewMembers();
                case 6 -> viewBooks();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addMember() {
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        members.put(id, new Member(id, name));
        System.out.println("Member added.");
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        books.put(id, new Book(id, title));
        System.out.println("Book added.");
    }

    static void issueBook() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        Member member = members.get(memberId);
        Book book = books.get(bookId);

        if (member == null || book == null) {
            System.out.println("Member or Book not found.");
            return;
        }

        if (book.isIssued) {
            System.out.println("Book is already issued.");
        } else {
            book.isIssued = true;
            member.borrowedBooks.add(bookId);
            System.out.println("Book issued successfully.");
        }
    }

    static void returnBook() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        Member member = members.get(memberId);
        Book book = books.get(bookId);

        if (member == null || book == null || !member.borrowedBooks.contains(bookId)) {
            System.out.println("Invalid return.");
            return;
        }

        book.isIssued = false;
        member.borrowedBooks.remove(bookId);
        System.out.println("Book returned successfully.");
    }

    static void viewMembers() {
        System.out.println("Members List:");
        for (Member m : members.values()) {
            System.out.println("ID: " + m.memberId + ", Name: " + m.name + ", Borrowed Books: " + m.borrowedBooks);
        }
    }

    static void viewBooks() {
        System.out.println("Books List:");
        for (Book b : books.values()) {
            System.out.println("ID: " + b.bookId + ", Title: " + b.title + ", Issued: " + b.isIssued);
        }
    }
}


