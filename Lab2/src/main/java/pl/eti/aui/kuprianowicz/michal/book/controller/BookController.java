package pl.eti.aui.kuprianowicz.michal.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.eti.aui.kuprianowicz.michal.book.dto.*;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.service.BookService;


import java.util.Optional;


@RestController
@RequestMapping("api/books")
public class BookController {

    private BookService bookService;


    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<GetBooksResponse> getBooks() {
        return ResponseEntity.ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll()));
    }


    @GetMapping("{id}")
    public ResponseEntity<GetBookResponse> getBook(@PathVariable("id") long id) {
        Optional<Book> character = bookService.find(id);
        return character.map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody CreateBookRequest request, UriComponentsBuilder builder) {
        Book book = CreateBookRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
        book = bookService.create(book);
        return ResponseEntity.created(builder.pathSegment("api", "books", "{id}").buildAndExpand(book.getId()).toUri()).build();
    }


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


 @PutMapping("{id}")
    public ResponseEntity<Void> updateBook(@RequestBody UpdateBookRequest request, @PathVariable("id") long id) {
        Optional<Book> character = bookService.find(id);
        if (character.isPresent()) {
            UpdateBookRequest.dtoToEntityUpdater().apply(character.get(), request);
            bookService.update(character.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{id}/title")
    public ResponseEntity<Void> updateBookAge(@RequestBody UpdateBookRequest request, @PathVariable("id") long id) {
        Optional<Book> character = bookService.find(id);
        if (character.isPresent()) {
            UpdateBookTitleRequest.dtoToEntityUpdater().apply(character.get(), request);
            bookService.update(character.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
