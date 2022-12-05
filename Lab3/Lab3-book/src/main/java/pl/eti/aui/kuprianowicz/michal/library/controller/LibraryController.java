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




    @PostMapping
    public ResponseEntity<Void> createLibrary(@RequestBody CreateLibraryRequest request, UriComponentsBuilder builder) {
        Library library = CreateLibraryRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
        library = libraryService.create(library);
        return ResponseEntity.created(builder.pathSegment("api", "libraries", "{libraryId}").buildAndExpand(library.getLibraryId()).toUri()).build();
    }


    @DeleteMapping("{libraryId}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable("libraryId") Long libraryId) {
        Optional<Library> library = libraryService.find(libraryId);
        if (library.isPresent()) {
            /*// Delete the library's books first
            List<Book> booksOFTheLibrary = bookService.findAll(library.get());
            for(Book s : booksOFTheLibrary){
                bookService.delete(s.getId());
            }*/
            // Then delete the library
            libraryService.delete(libraryId);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
