package pl.eti.aui.kuprianowicz.michal.book.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.repository.BookRepository;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;

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

    public Optional<Book> find(Library library, Long id) {
        return repository.findByIdAndLibrary(id,library);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findAll(Library library) {
        return repository.findAllByLibrary(library);
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

            repository.setBookTitleById(id, newTitle);
        });
    }



}

