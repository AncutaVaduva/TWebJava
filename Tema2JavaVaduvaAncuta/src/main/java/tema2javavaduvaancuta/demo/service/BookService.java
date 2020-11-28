package tema2javavaduvaancuta.demo.service;

import org.springframework.stereotype.Service;
import tema2javavaduvaancuta.demo.model.Book;
import tema2javavaduvaancuta.demo.model.Member;
import tema2javavaduvaancuta.demo.repository.BookRepository;

import java.util.List;
import java.util.Optional;

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

    public List<Member> getAllMembers(){
        return bookRepository.getAllMembers();
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public void memberRentBook (String username, String title){
        bookRepository.memberRentBook(username, title);
    }

    public void memberReturnsBook (String username, String title) {
        this.bookRepository.memberReturnsBook(username,title);
    }

    public List<String> booksRentedByMember(String username){
        return this.bookRepository.booksRentedByMember(username);
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

    public Book getBook(String title) {
        Optional<Book> bookOptional = bookRepository.getBook(title);
        if(bookOptional.isPresent()) {
            return bookOptional.get();//we return the bank account, if it was found
        } else {
            throw new RuntimeException("Book not found");
        }
    }
}

