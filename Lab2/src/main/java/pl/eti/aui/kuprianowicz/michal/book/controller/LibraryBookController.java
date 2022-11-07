package pl.eti.aui.kuprianowicz.michal.book.controller;


import pl.eti.aui.kuprianowicz.michal.book.dto.GetBookResponse;
import pl.eti.aui.kuprianowicz.michal.book.dto.UpdateBookRequest;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.eti.aui.kuprianowicz.michal.book.dto.CreateBookRequest;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.service.BookService;
import pl.eti.aui.kuprianowicz.michal.library.service.LibraryService;

import java.util.Optional;

@RestController
@RequestMapping("api/libraries/{id}/books")
public class LibraryBookController {


    private final BookService bookService;
    private final LibraryService libraryService;

    @Autowired
    public LibraryBookController(LibraryService libraryService, BookService bookService)
    {
        this.libraryService=libraryService;
        this.bookService=bookService;
    }

    /*// returns list of books in library converted to JSON or not found
    @GetMapping
    public ResponseEntity<GetLibrariesResponse> getBooksInLibrary(@PathVariable("id") Long id) {
        Optional<Library> library = libraryService.find(id);
        return library.map(value -> ResponseEntity.ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    // returns single book from selected library in JSON or not found
    @GetMapping("{id}")
    public ResponseEntity<GetBookResponse> getBookFromLibrary(@PathVariable("libraryId") long libraryId,
                                                              @PathVariable("id") long id) {
        Optional<Library> library = libraryService.find(libraryId);
        if (library.isPresent()) {
            Optional<Book> book = bookService.find(id);
            return book.map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //response with location builder header or not found
    @PostMapping
    public ResponseEntity<Void> createBookInLibrary(@PathVariable("id") long id,
                                                @RequestBody CreateBookRequest request,
                                                UriComponentsBuilder builder) {
        Optional<Library> library = libraryService.find(id);
        if (library.isPresent()) {
            Book book = CreateBookRequest
                    .dtoToEntityMapper()
                    .apply(request);
            book = bookService.create(book);
            return ResponseEntity.created(builder.pathSegment("api", "libraries", "{id}", "books", "{id}")
                    .buildAndExpand(library.get().getId(), book.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // deletes selected book from selected library, accepted or not found
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("libraryId") long libraryId,
                                                @PathVariable("id") long id) {
        Optional<Library> library = libraryService.find(libraryId);
        if (library.isPresent()) {
            Optional<Book> book = bookService.find(id);
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

    // update selected book from selected library, accepted or not found
    @PutMapping("{id}")
    public ResponseEntity<Void> updateCharacter(@PathVariable("libraryId") long libraryId,
                                                @RequestBody UpdateBookRequest request,
                                                @PathVariable("id") long id) {
        Optional<Library> library = libraryService.find(libraryId);
        if (library.isPresent()) {
            Optional<Book> book = bookService.find(id);
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
