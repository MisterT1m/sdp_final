package Library;

import Library.Controls.ManagerControls;
import Library.Controls.UserControls;
import Library.Factory.MemberUserFactory;
import Library.Factory.UserFactory;
import Library.Items.User;

public class Library {

    public static void main(String[] args) {
        DataBase db = new DataBase("src/Library/data.json");
        UserFactory uf = new MemberUserFactory();
        Login login = new Login(db, uf);
        
        User user = login.menu();
        if (user == null) {
            return;
        }
        
        System.out.println("Successfully logged in!");
        if (user.getAccessLevel() == 0) {
            new UserControls(user, db).menu();
        } else if (user.getAccessLevel() == 1) {
            new ManagerControls(user, db).menu();
        }
        db.saveData();
    }
}