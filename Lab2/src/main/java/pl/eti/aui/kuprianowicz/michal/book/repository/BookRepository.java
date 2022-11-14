/*
package pl.eti.aui.kuprianowicz.michal.book.repository;

import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    */
/*Optional<Book> findById(long id);

    Optional<Book> findByTitle(String title);


    void setBookTitleByAuthor(Long id, String newTitle);

    @Modifying
    @Query("update Book b set b.title = ?2 where b.Id = ?1")
    void setBookTitleById(Long id, String newTitle);*//*




}



*/
/*
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
*/

