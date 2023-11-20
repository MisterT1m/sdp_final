package Library.Factory;

import Library.Items.User;

public class AdminUserFactory extends UserFactory {
    @Override
    public
    User createUser(String id, String password, String group, Integer accessLevel) {
        return new User(id, password, "Admin", group, accessLevel);
    }
}
