package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");
        Author alex = new Author();
        alex.setFirstName("Alex");
        alex.setLastName("Jones");

        Author savedEric = authorRepository.save(eric);
        Author savedAlex = authorRepository.save(alex);

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456789");
        Book noEJB = new Book();
        ddd.setTitle("J2EE Development without EJB");
        ddd.setIsbn("987654321");

        Book savedDDD = bookRepository.save(ddd);
        Book savedNoEJB = bookRepository.save(noEJB);

        Publisher penguin = new Publisher();
        penguin.setName("Penguin");
        penguin.setAddress("123 Main St");
        penguin.setCity("New York");
        penguin.setZip("12345");

        Publisher savedPenguin = publisherRepository.save(penguin);

        savedEric.getBooks().add(savedDDD);
        savedAlex.getBooks().add(savedNoEJB);

        savedDDD.setPublisher(savedPenguin);
        savedNoEJB.setPublisher(savedPenguin);

        savedPenguin.getBooks().add(savedDDD);
        savedPenguin.getBooks().add(savedNoEJB);

        authorRepository.save(savedEric);
        authorRepository.save(savedAlex);
        bookRepository.save(savedDDD);
        bookRepository.save(savedNoEJB);
        publisherRepository.save(savedPenguin);

        System.out.println("In BootstrapData");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
