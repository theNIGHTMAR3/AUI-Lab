
package pl.eti.aui.kuprianowicz.michal.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pl.eti.aui.kuprianowicz.michal.book.dto.*;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.service.BookService;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import pl.eti.aui.kuprianowicz.michal.library.service.LibraryService;


import java.util.Optional;

@RestController
@RequestMapping("api/libraries/{id}/books")
public class LibraryBookController {

    private final BookService bookService;
    

    private final LibraryService libraryService;

    
    @Autowired
    public LibraryBookController(BookService bookService, LibraryService libraryService) {
        this.bookService = bookService;
        this.libraryService = libraryService;
    }


    /*@GetMapping
    public ResponseEntity<GetBooksResponse> getCharacters(@PathVariable("id") String id) {
        Optional<Library> library = libraryService.find(id);
        return library.map(value -> ResponseEntity.ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/


    @GetMapping("{id}")
    public ResponseEntity<GetBookResponse> getCharacter(@PathVariable("id") long libraryId,
                                                        @PathVariable("id") long id) {
        Optional<Library> library = libraryService.find(libraryId);
        if (library.isPresent()) {
            Optional<Book> book = bookService.find(library.get(), id);
            return book.map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Void> createCharacter(@PathVariable("id") long id,
                                                @RequestBody CreateBookRequest request,
                                                UriComponentsBuilder builder) {
        Optional<Library> library = libraryService.find(id);
        if (library.isPresent()) {
            Book book = CreateBookRequest
                    .dtoToEntityMapper(library::get)
                    .apply(request);
            book = bookService.create(book);
            return ResponseEntity.created(builder.pathSegment("api", "libraries", "{id}", "books", "{id}")
                    .buildAndExpand(library.get().getId(), book.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable("id") long libraryId,
                                                @PathVariable("id") long id) {
        Optional<Library> library = libraryService.find(libraryId);
        if (library.isPresent()) {
            Optional<Book> book = bookService.find(library.get(), id);
            if (book.isPresent()) {
                bookService.delete(book.get().getId());
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCharacter(@PathVariable("id") long LibraryId,
                                                @RequestBody UpdateBookRequest request,
                                                @PathVariable("id") long id) {
        Optional<Library> library = libraryService.find(LibraryId);
        if (library.isPresent()) {
            Optional<Book> book = bookService.find(library.get(), id);
            if (book.isPresent()) {
                UpdateBookRequest.dtoToEntityUpdater().apply(book.get(), request);
                bookService.update(book.get());
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}