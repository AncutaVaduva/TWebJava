package tema2javavaduvaancuta.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tema2javavaduvaancuta.demo.model.Book;
import tema2javavaduvaancuta.demo.model.Member;
import tema2javavaduvaancuta.demo.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addMember")
    public void createMember (@RequestBody Member member){
        bookService.addMember(member.getLastName(), member.getFirstName(), member.getUsername());
    }

    @GetMapping("/allMembers")
        public List<Member> getAllMembers(){
            return bookService != null ? bookService.getAllMembers() : null;
        }

    @PostMapping("/addBook")
    public void addBook (@RequestBody Book book){
        bookService.addBook(book.getTitle(),book.getAuthor(),book.getCategory(),book.getNumberOfAvailableCopies());
    }

    @GetMapping("/allBooks")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/memberRentBook/{username}/{title}")
    public void memberRentBook(@PathVariable String username, @PathVariable String title){
        bookService.memberRentBook(username,title);
    }

    @PutMapping("/extendRepoForBook/{title}/{nr}")
    public void extendRepoForBook(@PathVariable String title, @PathVariable Integer nr){
        bookService.extendBookRepository(title,nr);
    }

    @GetMapping("/bookRentedByUser/{username}")
    public List<String> bookRentedByUser(@PathVariable String username){
        return bookService.booksRentedByMember(username);
    }

    @GetMapping("/availableBook/{title}")
    public Book getBook( @PathVariable String title) {
        return bookService.getBook(title);
    }
}
