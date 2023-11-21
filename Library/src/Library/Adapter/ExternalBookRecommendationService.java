package Library.Adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExternalBookRecommendationService {

    private Map<String, List<String>> recommendations;

    public ExternalBookRecommendationService() {
        // Initialize the recommendation mapping
        recommendations = new HashMap<>();
        recommendations.put("2222", new ArrayList<String>() {{
            add("Avatar");
        }});
        recommendations.put("812735162", new ArrayList<String>() {{
            add("Geniology of Mind");
        }});
        // Add more mappings as required
    }

    public List<String> getRecommendations(List<String> borrowedIsbns) {
        List<String> recommendedBooks = new ArrayList<>();
        
        for (String isbn : borrowedIsbns) {
            List<String> isbnRecommendations = recommendations.getOrDefault(isbn, new ArrayList<>());
            recommendedBooks.addAll(isbnRecommendations);
        }

        return recommendedBooks.stream().distinct().collect(Collectors.toList());
    }
}
