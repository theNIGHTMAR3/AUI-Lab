/*
package pl.eti.aui.kuprianowicz.michal.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;
import pl.eti.aui.kuprianowicz.michal.book.service.BookService;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import pl.eti.aui.kuprianowicz.michal.library.service.LibraryService;

import javax.annotation.PostConstruct;

import java.util.Arrays;


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
                .Id(1L)
                .name("empik")
                .address("Niepodleglosci 130")
                .establishedYear(1990)
                .build();

        Library ateneum= Library.builder()
                .Id(2L)
                .name("Ateneum")
                .address("3 Maja")
                .establishedYear(2005)
                .build();

        libraryService.create(empik);
        libraryService.create(ateneum);


        Book LOTR= Book.builder()
                .Id(1L)
                .title("The Lord of the Rings")
                .author("J.R.R. Tolkien")
                .year(1968)
                .genre("fantasy")
                .existsIn(ateneum)
                .build();

        Book hobbit= Book.builder()
                .Id(2L)
                .title("The Hobbit")
                .author("J.R.R. Tolkien")
                .year(1937)
                .genre("fantasy")
                .existsIn(ateneum)
                .build();

        Book year1984= Book.builder()
                .Id(3L)
                .title("Nineteen Eighty-Four")
                .author("George Orwell")
                .year(1949)
                .genre("fiction")
                .existsIn(empik)
                .build();

        Book potop= Book.builder()
                .Id(4L)
                .title("Potop")
                .author("Henryk Sienkiewicz")
                .year(1886)
                .genre("historical")
                .existsIn(empik)
                .build();

        bookService.create(LOTR);
        bookService.create(hobbit);
        bookService.create(year1984);
        bookService.create(potop);


    }


}
*/
