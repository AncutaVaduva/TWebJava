package repository;

import model.Book;
import model.Member;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookRepository {
    private HashMap<String,Book> allBooks;
    private HashMap<String,Book> availableBooks;
    private HashMap<String,List<String>> rentedBooks;
    private HashMap<String,Member> members;
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

    public void addBook (Book book){
        setCountBooks(getCountBooks()+1);
        this.allBooks.put(book.getTitle(),book);
        this.availableBooks.put(book.getTitle(),book);//when is added a book is automatically available for rent
        System.out.println("The book was added");
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

    public void booksRentedByMember (String username){
        if(this.rentedBooks.containsKey(username))
            System.out.println("Books rented by "+username+"\n\t"+this.rentedBooks.get(username));
        else
            System.out.println("Member "+username+" didn't rent any books!");
    }

    public void availableBooks(){
        System.out.println("Available books are:\n\t"+this.availableBooks.keySet());
    }
}
