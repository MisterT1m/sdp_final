package Library.Adapter;

import java.util.List;
import java.util.stream.Collectors;

import Library.Items.Book;
import Library.Items.User;

public class BookRecommendationAdapter implements BookRecommendation {
    private ExternalBookRecommendationService recommendationService;

    public BookRecommendationAdapter(ExternalBookRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Override
    public List<String> recommendBooks(User user) {
        List<String> borrowedIsbns = user.getBorrowedBooks().stream()
                                         .map(Book::getIsbn)
                                         .collect(Collectors.toList());

        return recommendationService.getRecommendations(borrowedIsbns);
    }
}
