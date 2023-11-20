package Library.Controls;

import Library.DataBase;
import Library.Controls.Strategy.AddNewBookAction;
import Library.Controls.Strategy.AddNewUserAction;
import Library.Controls.Strategy.BorrowABookAction;
import Library.Controls.Strategy.MenuAction;
import Library.Controls.Strategy.MyInfoAction;
import Library.Controls.Strategy.ReturnABookAction;
import Library.Controls.Strategy.ShowBooksAction;
import Library.Items.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ManagerControls implements Controls {

    private Scanner in;
    private Map<Integer, MenuAction> actions;


    
      public ManagerControls(User user, DataBase db) {
        this.in = new Scanner(System.in);

        actions = new HashMap<>();
        actions.put(1, new MyInfoAction(user));
        actions.put(2, new AddNewBookAction(in, db));
        actions.put(3, new AddNewUserAction(in, db));
        actions.put(4, new ShowBooksAction(db, user));
        actions.put(5, new BorrowABookAction(in, db, user));
        actions.put(6, new ReturnABookAction(in, db, user));
    }

    @Override
    public void menu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main menu ===\n");

            System.out.println(
                "1. My info\n"+
                "2. Add a new book\n"+
                "3. Add a new user\n"+
                "4. Show all available books\n"+
                "5. Borrow a book\n"+
                "6. Return a book\n"+
                "7. Quit"
            );

            System.out.print(": ");
            int option = in.nextInt();
            in.nextLine();

            if (actions.containsKey(option)) {
                actions.get(option).execute();
            } else if (option == 7) {
                running = false;
            } else {
                System.out.println("Invalid option!");
            }
        }
            System.out.println();
    
        System.out.println("Goodbye :)");
    }   
}
    