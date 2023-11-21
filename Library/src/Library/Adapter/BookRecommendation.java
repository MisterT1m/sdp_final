package Library.Adapter;

import java.util.List;

import Library.Items.User;

public interface BookRecommendation {
    List<String> recommendBooks(User user);
}

