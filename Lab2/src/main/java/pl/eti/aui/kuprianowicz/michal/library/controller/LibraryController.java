package pl.eti.aui.kuprianowicz.michal.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.service.BookService;
import pl.eti.aui.kuprianowicz.michal.library.dto.*;
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


    /*@GetMapping
    public ResponseEntity<GetLibrariesResponse> getLibrarys() {
        return ResponseEntity.ok(GetLibrarysResponse.entityToDtoMapper().apply(libraryService.findAll()));
    }*/


    @GetMapping("{id}")
    public ResponseEntity<GetLibraryResponse> getLibrary(@PathVariable("id") Long id) {
        Optional<Library> library = libraryService.find(id);
        return library.map(value -> ResponseEntity.ok(GetLibraryResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> createLibrary(@RequestBody CreateLibraryRequest request, UriComponentsBuilder builder) {
        Library library = CreateLibraryRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
        library = libraryService.create(library);
        return ResponseEntity.created(builder.pathSegment("api", "libraries", "{id}").buildAndExpand(library.getId()).toUri()).build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable("id") Long id) {
        Optional<Library> library = libraryService.find(id);
        if (library.isPresent()) {
            // Delete the library's books first
            List<Book> booksOFTheLibrary = bookService.findAll(library.get());
            for(Book s : booksOFTheLibrary){
                bookService.delete(s.getId());
            }
            // Then delete the library
            libraryService.delete(id);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


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
