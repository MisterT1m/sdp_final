package Library.Items.Decorator;

public class ReviewDecorator extends BookDecorator {
    private String review;

    public ReviewDecorator(IBook decoratedBook, String review) {
        super(decoratedBook);
        this.review = review;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\nReview: " + review;
    }

    @Override
    public String getTitle() {
        throw new UnsupportedOperationException("Unimplemented method 'getTitle'");
    }

    @Override
    public String getIsbn() {
        throw new UnsupportedOperationException("Unimplemented method 'getIsbn'");
    }

    @Override
    public String getGenre() {
        throw new UnsupportedOperationException("Unimplemented method 'getGenre'");
    }

    @Override
    public String getAuthor() {
        throw new UnsupportedOperationException("Unimplemented method 'getAuthor'");
    }

    @Override
    public Integer getYear() {
        throw new UnsupportedOperationException("Unimplemented method 'getYear'");
    }

    @Override
    public Integer getQuantity() {
        throw new UnsupportedOperationException("Unimplemented method 'getQuantity'");
    }

    @Override
    public Integer getAccessLevel() {
        throw new UnsupportedOperationException("Unimplemented method 'getAccessLevel'");
    }
}
