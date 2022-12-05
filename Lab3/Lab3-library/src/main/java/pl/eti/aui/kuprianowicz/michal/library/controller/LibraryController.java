package pl.eti.aui.kuprianowicz.michal.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import pl.eti.aui.kuprianowicz.michal.library.dto.*;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import pl.eti.aui.kuprianowicz.michal.library.service.LibraryService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/libraries")
public class LibraryController {

    
    private final LibraryService libraryService;



    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @GetMapping
    public ResponseEntity<GetLibrariesResponse> getLibraries() {
        return ResponseEntity.ok(GetLibrariesResponse.entityToDtoMapper().apply(libraryService.findAll()));
    }


    @GetMapping("{libraryId}")
    public ResponseEntity<GetLibraryResponse> getLibrary(@PathVariable("libraryId") Long libraryId) {
        Optional<Library> library = libraryService.find(libraryId);
        return library.map(value -> ResponseEntity.ok(GetLibraryResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> createLibrary(@RequestBody CreateLibraryRequest request, UriComponentsBuilder builder) {
        Library library = CreateLibraryRequest
                .dtoToEntityMapper()
                .apply(request);
        //library = libraryService.create(library);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "libraries", "{libraryId}")
                        .buildAndExpand(library.getLibraryId()).toUri())
                .build();
    }


    @DeleteMapping("{libraryId}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable("libraryId") Long libraryId) {
        Optional<Library> library = libraryService.find(libraryId);
        if (library.isPresent()) {
            // Then delete the library
            libraryService.delete(libraryId);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{libraryId}")
    public ResponseEntity<Void> updateLibrary(@RequestBody UpdateLibraryRequest request, @PathVariable("libraryId") Long libraryId) {
        Optional<Library> library = libraryService.find(libraryId);
        if (library.isPresent()) {
            UpdateLibraryRequest.dtoToEntityUpdater().apply(library.get(), request);
            libraryService.update(library.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
