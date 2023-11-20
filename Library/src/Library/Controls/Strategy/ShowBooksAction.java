package Library.Controls.Strategy;

import Library.DataBase;
import Library.Items.Book;
import Library.Items.User;

public class ShowBooksAction implements MenuAction {
    private DataBase db;
    private User user;

    public ShowBooksAction(DataBase db, User user) {
        this.db = db;
        this.user = user;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Available books ===\n");
    
    if (db.getBooks().size() == 0) {
        System.out.println("Unfortunately there is not books in Library");
        return;
    }
    
    for (Book book: db.getBooks()) {
        if (book.getQuantity() == 0 && book.getAccessLevel() > user.getAccessLevel()) {
            continue;
        }
        
        System.out.println(book.toString());
    }
    }
}

