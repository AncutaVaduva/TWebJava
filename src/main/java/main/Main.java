package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.BookService;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        BookService service = context.getBean(BookService.class);

        service.addMember("Popescu","Maria","user1");
        service.addMember("Vasilescu","Andrei","user2");
        service.addMember("Alexandrescu","Mihai","user3");

        service.addBook("Stapanul inelelor","J.R.R. Tolkien","Fantasy ",15);
        service.addBook("Codul lui Da Vinci","Dan Brown","Mystery",6);
        service.addBook("Pe aripile vantului","Margaret Mitchell","Historical Fiction",10);
        service.addBook("Mizerabilii","Victor Hugo","Historical Fiction",3);


        service.memberRentBook("user1","Codul lui Da Vinci");
        service.memberRentBook("user1","Mizerabilii");
        service.booksRentedByMember("user1");

        service.decreasesStockOfBook("Mizerabilii",2);
        service.memberRentBook("user2","Mizerabilii");
        service.extendBookRepository("Mizerabilii",1);
        service.memberRentBook("user2","Mizerabilii");
        service.booksRentedByMember("user2");

        service.booksRentedByMember("user3");
        service.memberRentBook("user3","Mizerabilii");
        service.memberRentBook("user3","Pe aripile vantului");
        service.memberRentBook("user3","Codul lui Da Vinci");
        service.booksRentedByMember("user3");
        service.memberReturnsBook("user2","Mizerabilii");
        service.memberRentBook("user3","Mizerabilii");
        service.booksRentedByMember("user3");


        service.memberRentBook("user1","Micul Print");

        service.memberRentBook("user4","Micul Print");

        service.availableBooks();

        service.addMember("Popa","Adriana","user2");
    }
}
