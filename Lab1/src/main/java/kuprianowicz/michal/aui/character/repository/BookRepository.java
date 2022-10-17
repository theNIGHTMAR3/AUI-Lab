package kuprianowicz.michal.aui.character.repository;

import kuprianowicz.michal.aui.character.entity.Book;
import kuprianowicz.michal.aui.datastore.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private DataStore store;

    @Autowired
    public BookRepository(DataStore store) {
        this.store=store;
    }


    public Optional<Book> find(Long id) {
        return store.findBookById(id);
    }


    public List<Book> findAll() {
        return store.findAllBooks();
    }

    public void create(Book entity) {
        store.createBookFromOtherBook(entity);
    }


    public void delete(Book entity) {
        store.deleteBook(entity.getId());
    }


    public void update(Book entity) {
        store.updateBook(entity);
    }


    public Optional<Book> findByIdAndTitle(Long id, String title) {
        return store.getBookStream()
                .filter(book -> book.getTitle().equals(title))
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }


    public List<Book> findAllByTitle(String title) {
        return store.getBookStream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    public List<Book> findAllByAuthor(String author) {
        return store.getBookStream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

}
