package pl.eti.aui.kuprianowicz.michal.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.service.BookService;
import pl.eti.aui.kuprianowicz.michal.library.dto.CreateLibraryRequest;
import pl.eti.aui.kuprianowicz.michal.library.dto.GetLibrariesResponse;
import pl.eti.aui.kuprianowicz.michal.library.dto.GetLibraryResponse;
import pl.eti.aui.kuprianowicz.michal.library.dto.UpdateLibraryRequest;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import pl.eti.aui.kuprianowicz.michal.library.service.LibraryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/libraries")
public class LibraryController {

    private final LibraryService libraryService;
    private BookService bookService;

    @Autowired
    public LibraryController(LibraryService libraryService, BookService bookService) {
        this.libraryService = libraryService;
        this.bookService = bookService;
    }

    // converts list of libraries to JSON
    @GetMapping
    public ResponseEntity<GetLibrariesResponse> getTeachers() {
        return ResponseEntity.ok(GetLibrariesResponse.entityToDtoMapper().apply(libraryService.findAll()));
    }

    // returns single library in JSON or 404 when not found
    @GetMapping("{id}")
    public ResponseEntity<GetLibraryResponse> getTeacher(@PathVariable("id") Long id) {
        Optional<Library> library = libraryService.find(id);
        return library.map(value -> ResponseEntity.ok(GetLibraryResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // returns response with location header
    @PostMapping
    public ResponseEntity<Void> createLibrary(@RequestBody CreateLibraryRequest request, UriComponentsBuilder builder) {
        Library library = CreateLibraryRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
        library = libraryService.create(library);
        return ResponseEntity.created(builder.pathSegment("api", "libraries", "{id}").buildAndExpand(library.getId()).toUri()).build();
    }

    // deletes library, accepted or not found
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable("id") Long id) {
        Optional<Library> library = libraryService.find(id);
        if (library.isPresent()) {
            // Delete books inside library first
            List<Book> booksInLibrary = library.get().getBooks();
            for(Book b : booksInLibrary){
                bookService.delete(b.getId());
            }
            // Then delete the library
            libraryService.delete(id);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // updates selected library, requests data from JSON, accepted or not found
    @PutMapping("{id}")
    public ResponseEntity<Void> updateLibrary(@RequestBody UpdateLibraryRequest request, @PathVariable("id") Long id) {
        Optional<Library> library = libraryService.find(id);
        if (library.isPresent()) {
            UpdateLibraryRequest.dtoToEntityUpdater().apply(library.get(), request);
            libraryService.update(library.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
