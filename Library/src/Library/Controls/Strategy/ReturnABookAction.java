package Library.Controls.Strategy;

import java.util.Scanner;

import Library.DataBase;
import Library.Items.Book;
import Library.Items.User;
import Library.Items.Observer.NotificationSystem;
import Library.Items.Observer.UserObserver;

public class ReturnABookAction implements MenuAction {
    private Scanner in;
    private DataBase db;
    private User user;

    public ReturnABookAction(Scanner in, DataBase db, User user) {
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
            if (userBook == null) {
                System.out.println("You did not borrowed this book!");
                return;
            }
            
            book.setQuantity(book.getQuantity()+1);
            user.getBorrowedBooks().remove(userBook);
            
            // Creating an observer
            UserObserver notificationSystem = new NotificationSystem();

            // Registering the observer
            user.addObserver(notificationSystem);
            
            user.notifyObservers();
            System.out.println("Successfully returned a book!");
    }
}
