package Library.Controls.Strategy;

import java.util.Scanner;

import Library.DataBase;
import Library.Items.User;

public class AddNewUserAction implements MenuAction {
    private Scanner in;
    private DataBase db;

    public AddNewUserAction(Scanner in, DataBase db) {
        this.in = in;
        this.db = db;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Add a new user ===\n");
        
        System.out.print("Id: ");
        String id = in.nextLine();
        
        System.out.print("Name: ");
        String name = in.nextLine();
        
        System.out.print("Password: ");
        String password = in.nextLine();
        
        System.out.print("Group: ");
        String group = in.nextLine();
        
        System.out.print("Access level: ");
        Integer accessLevel = in.nextInt();
        in.nextLine();
        
        User newUser = new User(id, password, name, group, accessLevel);
        User exUser = db.getUser(newUser);
        if (exUser != null) {
            System.out.println("\nUser already exists!");
        } else {
            db.getUsers().add(newUser);
            System.out.println("\nSuccessfully created a new user!");
        }
    }
}
