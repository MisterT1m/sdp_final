package Library.Items.Observer;

import Library.Items.User;

public class NotificationSystem implements UserObserver {
    @Override
    public void update(User user) {
        // Logic to send notifications about user's actions
        System.out.println("Notification: User " + user.getId() + " has updated their borrowed books.");
    }
}
