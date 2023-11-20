package Library.Items.Decorator;

public abstract class BookDecorator implements IBook {
    protected IBook decoratedBook;

    public BookDecorator(IBook decoratedBook) {
        this.decoratedBook = decoratedBook;
    }

    @Override
    public String getDescription() {
        return decoratedBook.getDescription();
    }

}

