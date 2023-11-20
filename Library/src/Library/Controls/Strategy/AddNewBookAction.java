package Library.Controls.Strategy;

import java.util.Scanner;

import Library.DataBase;
import Library.Items.Book;

public class AddNewBookAction implements MenuAction {
    private Scanner in;
    private DataBase db;

    public AddNewBookAction(Scanner in, DataBase db) {
        this.in = in;
        this.db = db;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Add a new book ===\n");
        
        System.out.print("Title: ");
        String title = in.nextLine();
        
        System.out.print("ISBN: ");
        String isbn = in.nextLine();
        
        System.out.print("Genre: ");
        String genre = in.nextLine();
        
        System.out.print("Author: ");
        String author = in.nextLine();
        
        System.out.print("Year: ");
        Integer year = in.nextInt();
        in.nextLine();
        
        System.out.print("Quantity: ");
        Integer quantity = in.nextInt();
        in.nextLine();
        
        System.out.print("Access level: ");
        Integer accessLevel = in.nextInt();
        in.nextLine();
        
        Book newBook = new Book(title, isbn, genre, author, year, quantity, accessLevel);
        Book exBook = db.getBook(newBook);
        if (exBook != null) {
            exBook.setQuantity(exBook.getQuantity() + quantity);
            System.out.println("\nSuccessfully updated a book!");
        } else {
            db.getBooks().add(newBook);
            System.out.println("\nSuccessfully added a new book!");
        }
    }
}

