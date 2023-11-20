package Library.Controls.Strategy;

import java.util.Scanner;

import Library.DataBase;
import Library.Items.Book;
import Library.Items.User;
import Library.Items.Observer.NotificationSystem;
import Library.Items.Observer.UserObserver;

public class BorrowABookAction implements MenuAction {
    private Scanner in;
    private DataBase db;
    private User user;

    public BorrowABookAction(Scanner in, DataBase db, User user) {
        this.in = in;
        this.db = db;
        this.user = user;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Borrow a book ===\n");
    
    System.out.print("ISBN: ");
    String isbn = in.nextLine();
        Book book = db.getBook(isbn);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        Book userBook = db.getBookFromUser(user, isbn);
        if (userBook != null) {
            System.out.println("You already borrowed this book!");
            return;
        }
        
        if (book.getQuantity() == 0) {
            System.out.println("Not enough books!");
            return;
        }
        
        book.setQuantity(book.getQuantity()-1);
        Book singleBook = new Book(
            book.getTitle(),
            book.getIsbn(),
            book.getGenre(),
            book.getAuthor(),
            book.getYear(),
            1,
            book.getAccessLevel()
            );
            
            user.getBorrowedBooks().add(singleBook);
            
            // Creating an observer
            UserObserver notificationSystem = new NotificationSystem();

            // Registering the observer
            user.addObserver(notificationSystem);

            
            user.notifyObservers();
            System.out.println("Successfully borrowed a book!");
    }
}
