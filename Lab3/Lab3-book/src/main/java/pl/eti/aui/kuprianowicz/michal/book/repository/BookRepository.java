
package pl.eti.aui.kuprianowicz.michal.book.repository;

import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

Optional<Book> findById(long id);

    Optional<Book> findByIdAndLibrary(Long Id, Library library);

    List<Book> findAllByLibrary(Library library);

    @Modifying
    @Query("update Book b set b.title = ?2 where b.id = ?1")
    void setBookTitleById(Long id, String newTitle);
}





