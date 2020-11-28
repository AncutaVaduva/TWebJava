package service;

import model.Book;
import model.Member;
import org.springframework.stereotype.Service;
import repository.BookRepository;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String title, String author, String category, Integer numberOfCopies){
        Book book= new Book(title,author, category,numberOfCopies);
        bookRepository.addBook(book);
    }

    public void addMember(String lastName, String firstName, String username){
        Member member = new Member(lastName, firstName, username);
        bookRepository.addMember(member);
    }

    public void memberRentBook (String username, String title){
        bookRepository.memberRentBook(username, title);
    }

    public void memberReturnsBook (String username, String title) {
        this.bookRepository.memberReturnsBook(username,title);
    }

    public void booksRentedByMember(String username){
        this.bookRepository.booksRentedByMember(username);
    }

    public void extendBookRepository(String title, Integer number){
        this.bookRepository.extendBookRepository(title,number);
    }

    public void decreasesStockOfBook ( String title, Integer number) {
        this.bookRepository.decreasesStockOfBook(title, number);
    }

    public void availableBooks(){
        this.bookRepository.availableBooks();
    }
}
