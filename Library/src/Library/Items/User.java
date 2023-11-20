package Library.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Library.Items.Observer.UserObserver;

public class User {
    private String id;
    private String password;
    private String name;
    private String group;
    private Integer accessLevel;
    private ArrayList<Book> borrowedBooks;
    
    private List<UserObserver> observers = new ArrayList<>();


    public User(String id, String password, String name, String group, Integer accessLevel) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.group = group;
        this.accessLevel = accessLevel;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getAccessLevel() {
        return this.accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void addObserver(UserObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(UserObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (UserObserver observer : observers) {
            observer.update(this);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) || Objects.equals(name, user.name);
    }

    @Override
    public String toString() {
        String text = "ID: " + getId() + "\n" +
                      "Name:" + getName() + "\n" +
                      "Group: " + getGroup() + "\n" +
                      "Access level: " + getAccessLevel() + "\n" +
                      "Borrowed books: {";
        for (Book book: getBorrowedBooks()) {
            text += book.getTitle() + " (ISBN: " + book.getIsbn() + "), ";
        }
        text += "}";
        
        return text;
    }

    
}