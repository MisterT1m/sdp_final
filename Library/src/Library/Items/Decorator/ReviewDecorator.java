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
        throw new UnsupportedOperationException("Um'getTitle'");
    }

    @Override
    public String getIsbn() {
        throw new UnsupportedOperationException("Um 'getIsbn'");
    }

    @Override
    public String getGenre() {
        throw new UnsupportedOperationException("Um 'getGenre'");
    }

    @Override
    public String getAuthor() {
        throw new UnsupportedOperationException("Um 'getAuthor'");
    }

    @Override
    public Integer getYear() {
        throw new UnsupportedOperationException("Um 'getYear'");
    }

    @Override
    public Integer getQuantity() {
        throw new UnsupportedOperationException("Um 'getQuantity'");
    }

    @Override
    public Integer getAccessLevel() {
        throw new UnsupportedOperationException("Um 'getAccessLevel'");
    }
}
