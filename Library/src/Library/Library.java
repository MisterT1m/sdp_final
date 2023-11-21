package Library;

import java.util.List;

import Library.Adapter.BookRecommendation;
import Library.Adapter.BookRecommendationAdapter;
import Library.Adapter.ExternalBookRecommendationService;
import Library.Controls.ManagerControls;
import Library.Controls.UserControls;
import Library.Factory.MemberUserFactory;
import Library.Factory.UserFactory;
import Library.Items.User;

public class Library {

    public static void main(String[] args) {
        DataBase db = DataBase.getInstance("src/Library/data.json");//singleton
        UserFactory uf = new MemberUserFactory();
        Login login = new Login(db, uf);

         // Setting up the book recommendation adapter
        ExternalBookRecommendationService recommendationService = new ExternalBookRecommendationService();
        BookRecommendation recommendationAdapter = new BookRecommendationAdapter(recommendationService);
        
        User user = login.menu();
        if (user == null) {
            return;
        }
        
        System.out.println("Successfully logged in!");

        // Provide book recommendations
        List<String> recommendations = recommendationAdapter.recommendBooks(user);
        System.out.println("Recommended books for you: " + recommendations);

        if (user.getAccessLevel() == 0) {
            new UserControls(user, db).menu();
        } else if (user.getAccessLevel() == 1) {
            new ManagerControls(user, db).menu();
        }

        db.saveData();
    }
}
