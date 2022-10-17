package kuprianowicz.michal.aui.character.service;

import kuprianowicz.michal.aui.character.entity.Book;
import kuprianowicz.michal.aui.character.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository repository;


    @Autowired
    public BookService(BookRepository repo){
        this.repository=repo;
    }

    public Optional<Book> find(Long id) {
        return repository.find(id);
    }

    public Optional<Book> find(String title, Long id) {
        return repository.findByIdAndTitle(id, title);
    }


    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findAll(String author) {
        return repository.findAllByAuthor(author);
    }

    public void create(Book book) {
        repository.create(book);
    }

    public void update(Book book) {
        repository.update(book);
    }

    public void delete(Long id) {
        repository.delete(repository.find(id).orElseThrow());
    }

}
