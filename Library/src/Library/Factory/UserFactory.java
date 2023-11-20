package Library.Factory;

import Library.Items.User;

public abstract class UserFactory {
    public abstract User createUser(String id, String password, String group, Integer accessLevel);
}

