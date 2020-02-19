package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepo;
import guru.springframework.spring5webapp.repositories.BookRepo;
import guru.springframework.spring5webapp.repositories.PublisherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepo authorRep;
    private final BookRepo bookRep;
    private final PublisherRepo publisherRep;

    public BootStrapData(AuthorRepo authorRep, BookRepo bookRep, PublisherRepo publisherRep) {
        this.authorRep = authorRep;
        this.bookRep = bookRep;
        this.publisherRep = publisherRep;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in BootStrap");

        Author eric = new Author("Eric", "Evans");
        Book good = new Book("Domain Driven Design", "123456");
        Publisher publ = new Publisher();
        publ.setName("SFG Publishing");
        publ.setCity("St Petersburg");
        publ.setState("FL");

        publisherRep.save(publ);
        System.out.println("Number of publishers: " + publisherRep.count());

        eric.getBooks().add(good);
        good.getAuthors().add(eric);

        good.setPublisher(publ);
        publ.getBooks().add(good);

        authorRep.save(eric);
        bookRep.save(good);
        publisherRep.save(publ);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "87654376");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publ);
        publ.getBooks().add(noEJB);

        authorRep.save(rod);
        bookRep.save(noEJB);
        publisherRep.save(publ);

        System.out.println("Number of books: " + bookRep.count());
        System.out.println("Publisher number of books: " + publ.getBooks().size());
    }
}
