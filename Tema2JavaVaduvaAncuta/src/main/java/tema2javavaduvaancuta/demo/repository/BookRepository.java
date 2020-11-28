package tema2javavaduvaancuta.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tema2javavaduvaancuta.demo.model.Book;
import tema2javavaduvaancuta.demo.model.Member;

import java.util.*;

@Repository
public class BookRepository {
    private HashMap<String,Book> allBooks;
    private HashMap<String,Book> availableBooks;
    private HashMap<String, List<String>> rentedBooks;
    private HashMap<String, Member> members;
    private Integer countBooks = 0;

    public BookRepository() {
        this.allBooks = new HashMap<String, Book>();
        this.availableBooks = new HashMap<String, Book>();
        this.rentedBooks = new HashMap<String, List<String>>();
        this.members = new HashMap<String, Member>();
        System.out.println("Repo constructed");
    }

    public Integer getCountBooks() {
        return countBooks;
    }

    public void setCountBooks(Integer countBooks) {
        this.countBooks = countBooks;
    }

    public void addMember (Member member){
        if(this.members.get(member.getUsername()) == null){
            this.members.put(member.getUsername(), member);
            System.out.println("The member was added");
        }
        else{
            throw new IllegalArgumentException ("Already exists a member with username:"+member.getUsername());
        }
    }

    public List<Member>getAllMembers(){
        return new ArrayList<Member>(members.values());
    }

    public void addBook (Book book){
        setCountBooks(getCountBooks()+1);
        this.allBooks.put(book.getTitle(),book);
        this.availableBooks.put(book.getTitle(),book);//when is added a book is automatically available for rent
        System.out.println("The book was added");
    }

    public List<Book> getAllBooks(){
        return new ArrayList<Book>(allBooks.values());
    }

    public void extendBookRepository(String title, Integer number){
        Book book = this.allBooks.get(title);
        book.setNumberOfCopies(book.getNumberOfCopies() + number);
        book.setNumberOfAvailableCopies(book.getNumberOfAvailableCopies() + number);
        this.availableBooks.put(title,book);
    }

    public void decreasesStockOfBook ( String title, Integer number){
        Book book = this.allBooks.get(title);
        book.setNumberOfCopies(book.getNumberOfCopies() - number);
        book.setNumberOfAvailableCopies(book.getNumberOfAvailableCopies() - number);
        if ( book.getNumberOfCopies() == 0 || book.getNumberOfAvailableCopies()<= 0)
            this.availableBooks.remove(title);
    }

    public void memberRentBook (String username, String title){
        if (this.members.get(username) != null) {
            if (this.allBooks.get(title) != null) {
                if (this.availableBooks.get(title) != null) {
                    if (this.rentedBooks.containsKey(username)) {
                        this.rentedBooks.get(username).add(title);
                    } else {
                        List<String> ls = new LinkedList<String>();
                        ls.add(title);
                        this.rentedBooks.put(username, ls);
                    }
                    System.out.println("The rent was made!");
                    Book book = this.availableBooks.get(title);
                    book.setNumberOfAvailableCopies(book.getNumberOfAvailableCopies() - 1);
                    if (book.getNumberOfAvailableCopies() == 0) {
                        this.availableBooks.remove(title);
                    }
                } else {
                    System.out.println("The book is not available!");
                }
            } else {
                System.out.println("Don't have the book in library!");
            }
        }
        else{
            System.out.println("You are not one of our members!");
        }
    }

    public void memberReturnsBook (String username, String title){
        Book book = this.allBooks.get(title);
        book.setNumberOfAvailableCopies(book.getNumberOfAvailableCopies() + 1);
        if ( book.getNumberOfAvailableCopies() > 0) {
            this.availableBooks.put(title,book);
        }
    }

    public List<String> booksRentedByMember (String username){
        if(this.rentedBooks.containsKey(username))
            return this.rentedBooks.get(username);
        else
            return null;
    }

    public void availableBooks(){
        System.out.println("Available books are:\n\t"+this.availableBooks.keySet());
    }

    public Optional<Book> getBook(String title) {
        List<Book> availableBooksC = new ArrayList<Book>(availableBooks.values());
        return availableBooksC.stream()
                //we search for the bank account with id matching the id sent in the request
                .filter(book -> book.getTitle().equals(title))
                .findFirst(); //in case no bank account was found, we return an empty Optional object, to avoid returning null
    }
}
