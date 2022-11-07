package pl.eti.aui.kuprianowicz.michal.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.eti.aui.kuprianowicz.michal.book.dto.*;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.service.BookService;

import java.util.Optional;


/**
 * REST controller for student resource. It does not return or receive entity objects but dto objects which present
 * only those fields which are converted to JSON.
 */

@RestController
@RequestMapping("api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // converting list of all books to JSON
    @GetMapping
    public ResponseEntity<GetBooksResponse> getBooks() {
        return ResponseEntity.ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll()));
    }

    // converts single book to JSON or 404 when not found
    @GetMapping("{id}")
    public ResponseEntity<GetBookResponse> getBook(@PathVariable("id") long id) {
        Optional<Book> character = bookService.find(id);
        return character.map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // requests new book from JSON
    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody CreateBookRequest request, UriComponentsBuilder builder) {
        Book book = CreateBookRequest
                .dtoToEntityMapper()
                .apply(request);
        book = bookService.create(book);
        return ResponseEntity.created(builder.pathSegment("api", "books", "{id}").buildAndExpand(book.getId()).toUri()).build();
    }

    // deletes selected book
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        Optional<Book> book = bookService.find(id);
        if (book.isPresent()) {
            bookService.delete(book.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // updates selected book
    @PutMapping("{id}")
    public ResponseEntity<Void> updateBook(@RequestBody UpdateBookRequest request, @PathVariable("id") long id) {
        Optional<Book> book = bookService.find(id);
        if (book.isPresent()) {
            UpdateBookRequest.dtoToEntityUpdater().apply(book.get(), request);
            bookService.update(book.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update books title
    @PutMapping("{id}/title")
    public ResponseEntity<Void> updateStudentAge(@RequestBody UpdateBookRequest request, @PathVariable("id") long id) {
        Optional<Book> book = bookService.find(id);
        if (book.isPresent()) {
            UpdateBookTitleRequest.dtoToEntityUpdater().apply(book.get(), request);
            bookService.update(book.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
