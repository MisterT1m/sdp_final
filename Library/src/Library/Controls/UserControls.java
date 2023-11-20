package Library.Controls;

import Library.DataBase;
import Library.Controls.Strategy.BorrowABookAction;
import Library.Controls.Strategy.MenuAction;
import Library.Controls.Strategy.MyInfoAction;
import Library.Controls.Strategy.ReturnABookAction;
import Library.Controls.Strategy.ShowBooksAction;
import Library.Items.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserControls implements Controls {
    private Scanner in;

    private Map<Integer, MenuAction> actions;

    public UserControls(User user, DataBase db) {
        this.in = new Scanner(System.in);

        actions = new HashMap<>();
        actions.put(1, new MyInfoAction(user));
        actions.put(2, new ShowBooksAction(db, user));
        actions.put(3, new BorrowABookAction(in, db, user));
        actions.put(4, new ReturnABookAction(in, db, user));
    }

  
    @Override
    public void menu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main menu ===\n");

            System.out.println(
                "1. My info\n"+
                "2. Show all available books\n"+
                "3. Borrow a book\n"+
                "4. Return a book\n"+
                "5. Quit"
            );

            System.out.print(": ");
            int option = in.nextInt();
            in.nextLine();

            if (actions.containsKey(option)) {
                actions.get(option).execute();
            } else if (option == 5) {
                running = false;
            } else {
                System.out.println("Invalid option!");
            }
        }
            System.out.println();
    
        System.out.println("Goodbye :)");
    }
}
