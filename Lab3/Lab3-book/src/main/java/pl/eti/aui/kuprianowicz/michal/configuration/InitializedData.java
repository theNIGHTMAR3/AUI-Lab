package pl.eti.aui.kuprianowicz.michal.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.service.BookService;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import pl.eti.aui.kuprianowicz.michal.library.service.LibraryService;

import javax.annotation.PostConstruct;


@Component
public class InitializedData {

    private final BookService bookService;

    private final LibraryService libraryService;

    @Autowired
    public InitializedData(BookService bookService,LibraryService libraryService){
        this.bookService=bookService;
        this.libraryService=libraryService;
    }

    @PostConstruct
    private synchronized void init()
    {

        Library empik= Library.builder()
                .libraryId(1L)
                .build();

        Library ateneum= Library.builder()
                .libraryId(2L)
                .build();

        libraryService.create(empik);
        libraryService.create(ateneum);


        Book LOTR= Book.builder()
                .id(1L)
                .title("The Lord of the Rings")
                .author("J.R.R. Tolkien")
                .year(1968)
                .genre("fantasy")
                .library(ateneum)
                .build();

        Book hobbit= Book.builder()
                .id(2L)
                .title("The Hobbit")
                .author("J.R.R. Tolkien")
                .year(1937)
                .genre("fantasy")
                .library(ateneum)
                .build();

        Book year1984= Book.builder()
                .id(3L)
                .title("Nineteen Eighty-Four")
                .author("George Orwell")
                .year(1949)
                .genre("fiction")
                .library(empik)
                .build();

        Book potop= Book.builder()
                .id(4L)
                .title("Potop")
                .author("Henryk Sienkiewicz")
                .year(1886)
                .genre("historical")
                .library(empik)
                .build();

        bookService.create(LOTR);
        bookService.create(hobbit);
        bookService.create(year1984);
        bookService.create(potop);


    }


}
