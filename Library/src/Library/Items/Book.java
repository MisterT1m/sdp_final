package Library.Items;

import java.util.Objects;

import Library.Items.Decorator.IBook;
import Library.Items.Decorator.ReviewDecorator;

public class Book  implements IBook {
    private String title;
    private String isbn;
    private String genre;
    private String author;
    private Integer year;
    private Integer quantity;
    private Integer accessLevel;

    public Book(String title, String isbn, String genre, String author, Integer year, Integer quantity, Integer accessLevel) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
        this.accessLevel = accessLevel;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAccessLevel() {
        return this.accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String getDescription() {
        return toString(); // Use the existing toString method for the base description
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book)) {
            return false;
        }

        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
               Objects.equals(isbn, book.isbn) &&
               Objects.equals(genre, book.genre) &&
               Objects.equals(author, book.author) &&
               Objects.equals(year, book.year) &&
               Objects.equals(accessLevel, book.accessLevel);
    }

    @Override
    public String toString() {
        IBook book = new Book(getTitle(), getIsbn(), getGenre(), getAuthor(), getYear(), getQuantity(), getAccessLevel());
        book = new ReviewDecorator(book, "A classic piece of American literature.");

        return "Title: " + getTitle() + "\n" +
               "ISBN: " + getIsbn() + "\n" +
               "Genre: " + getGenre() + "\n" +
               "Author: " + getAuthor() + "\n" +
               "Year: " + getYear() + "\n" +
               "Quantity: " + getQuantity() + "\n" +
               "Review: " + "A classic piece of American literature." + "\n"
               ;
               
    }
}
