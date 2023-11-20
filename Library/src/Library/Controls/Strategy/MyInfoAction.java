package Library.Controls.Strategy;
import Library.Items.User;

public class MyInfoAction implements MenuAction {
    private User user;

    public MyInfoAction(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        System.out.println("\n=== My info ===\n" + user.toString());
        
    }
}
