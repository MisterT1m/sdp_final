package Library;

import java.io.Reader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Iterator;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Library.Items.Book;
import Library.Items.User;

public class DataBase {

    private ArrayList<User> users;
    private ArrayList<Book> books;
    private String filepath;
    private static DataBase instance;

    public DataBase(String filepath) {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.filepath = filepath;
        try {
            loadData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Static method to get the instance
    public static DataBase getInstance(String filePath) {
        if (instance == null) {
            instance = new DataBase(filePath);
        }
        return instance;
    }

    public void loadData() throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(this.filepath);

        Object jsonObj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject) jsonObj;

        JSONArray users = (JSONArray) jsonObject.get("users");
        
        @SuppressWarnings("unchecked")
        Iterator<JSONObject> it = users.iterator();
        while (it.hasNext()) {
            JSONObject userObj = it.next();
            User user = new User(
                (String) userObj.get("id"),
                (String) userObj.get("password"),
                (String) userObj.get("name"),
                (String) userObj.get("group"),
                ((Long) userObj.get("accessLevel")).intValue()
            );

            JSONArray borrowedBooks = (JSONArray) userObj.get("borrowedBooks");
            
            @SuppressWarnings("unchecked")
            Iterator<JSONObject> it1 = borrowedBooks.iterator();
            while (it1.hasNext()) {
                JSONObject bookObj = it1.next();
                user.getBorrowedBooks().add(
                    new Book(
                        (String) bookObj.get("title"),
                        (String) bookObj.get("isbn"),
                        (String) bookObj.get("genre"),
                        (String) bookObj.get("author"),
                        ((Long) bookObj.get("year")).intValue(),
                        ((Long) bookObj.get("quantity")).intValue(),
                        ((Long) bookObj.get("accessLevel")).intValue()
                    )
                );
            }

            this.users.add(user);
        }

        JSONArray books = (JSONArray) jsonObject.get("books");

        @SuppressWarnings("unchecked")
        Iterator<JSONObject> it2 = books.iterator();
        while (it2.hasNext()) {
            JSONObject bookObj = it2.next();
            this.books.add(
                new Book(
                    (String) bookObj.get("title"),
                    (String) bookObj.get("isbn"),
                    (String) bookObj.get("genre"),
                    (String) bookObj.get("author"),
                    ((Long) bookObj.get("year")).intValue(),
                    ((Long) bookObj.get("quantity")).intValue(),
                    ((Long) bookObj.get("accessLevel")).intValue()
                )
            );
        }

        reader.close();
    }

    @SuppressWarnings("unchecked")
    public void saveData() {
        JSONObject obj = new JSONObject();

        JSONArray users = new JSONArray();
        for (User user: this.users) {
            JSONObject item = new JSONObject();
            item.put("id", user.getId());
            item.put("password", user.getPassword());
            item.put("name", user.getName());
            item.put("group", user.getGroup());
            item.put("accessLevel", user.getAccessLevel());

            JSONArray borrowedBooks = new JSONArray();
            for (Book book: user.getBorrowedBooks()) {
                JSONObject item2 = new JSONObject();
                item2.put("title", book.getTitle());
                item2.put("isbn", book.getIsbn());
                item2.put("genre", book.getGenre());
                item2.put("author", book.getAuthor());
                item2.put("year", book.getYear());
                item2.put("quantity", book.getQuantity());
                item2.put("accessLevel", book.getAccessLevel());

                borrowedBooks.add(item2);
            }

            item.put("borrowedBooks", borrowedBooks);
            users.add(item);
        }

        obj.put("users", users);

        JSONArray books = new JSONArray();
        for (Book book: this.books) {
            JSONObject item = new JSONObject();
            item.put("title", book.getTitle());
            item.put("isbn", book.getIsbn());
            item.put("genre", book.getGenre());
            item.put("author", book.getAuthor());
            item.put("year", book.getYear());
            item.put("quantity", book.getQuantity());
            item.put("accessLevel", book.getAccessLevel());

            books.add(item);
        }

        obj.put("books", books);


        try {
            FileWriter file = new FileWriter(this.filepath);
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Successfully saved all data!");

    }

    public Book getBook(Book book) {
        for (Book other: this.books) {
            if (book.equals(other)) {
                return other;
            }
        }
        return null;
    }

    public Book getBook(String isbn) {
        for (Book other: this.books) {
            if (other.getIsbn().equals(isbn)) {
                return other;
            }
        }
        return null;
    }

    public Book getBookFromUser(User user, String isbn) {
        for (Book other: user.getBorrowedBooks()) {
            if (other.getIsbn().equals(isbn)) {
                return other;
            }
        }
        return null;
    }

    public User getUser(User user) {
        for (User other: this.users) {
            if (user.equals(other)) {
                return other;
            }
        }
        return null;
    }

    public User getUser(String id) {
        for (User other: this.users) {
            if (other.getId().equals(id)) {
                return other;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
    
}
