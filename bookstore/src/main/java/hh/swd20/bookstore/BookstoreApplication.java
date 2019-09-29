package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);  // uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
//  testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository crepository) { 
		return (args) -> {
			log.info("save a couple of books"); 
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Novel"));
			crepository.save(new Category("Sci-Fi"));
			
			
			bookRepository.save(new Book("Harry Potter ja Viisasten kivi","J. K. Rowling", 1997, "951-31-1146-6", 5.0, crepository.findByName("Fantasy").get(0)));
			bookRepository.save(new Book("Värityskirja", "Marko Annala",2017, "978-952-01-1605-7", 14, crepository.findByName("Novel").get(0)));	
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
