package Library;

import java.util.Scanner;

import Library.Factory.UserFactory;
import Library.Items.User;

public class Login {

    private Scanner in;
    private DataBase db;
    private UserFactory userFactory;

     public Login(DataBase db, UserFactory userFactory) {
        this.db = db;
        this.in = new Scanner(System.in);
        this.userFactory = userFactory;
    }

    public User menu() {
        boolean running = true;
        User user = null;
        while (running) {
            System.out.println("\n=== Log in ===\n");

            System.out.println(
                "1. Log in\n"+
                "2. Quit"
            );

            System.out.print(": ");
            int option = in.nextInt();
            in.nextLine();

            switch (option){
                case 1:
                    user = login();
                    if (user != null) {
                        return user;
                    }
                    break;
                case 2:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
            System.out.println();
        }
        return null;
    }

    public User login() {
        System.out.println("\n=== Log in menu ===\n");

        System.out.print("ID: ");
        String id = in.nextLine();

        System.out.print("Password: ");
        String password = in.nextLine();

        User User = db.getUser(id);
        if (User == null) {
            System.out.println("User not found!");
            return null;
        }

        if (!User.getPassword().equals(password)) {
            System.out.println("Invalid password!");
            return null;
        }

        // factory to create user with specific characteristics
        return userFactory.createUser(id, password, User.getGroup(), User.getAccessLevel());
    }
}
