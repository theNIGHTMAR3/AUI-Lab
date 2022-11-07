package pl.eti.aui.kuprianowicz.michal.book.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.repository.BookRepository;

import javax.transaction.Transactional;
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
        return repository.findById(id);
    }

    public Optional<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findAllWithAuthor(String author) {
        return repository.findAllByAuthor(author);
    }

    @Transactional
    public Book create(Book book) {
        return repository.save(book);
    }

    @Transactional
    public void update(Book book) {
        repository.save(book);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);

    }

    @Transactional
    public void updateTitle(Long id, String newTitle) {
        repository.findById(id).ifPresent(book -> {

            repository.setBookTitleByAuthor(id, newTitle);
        });
    }

}
