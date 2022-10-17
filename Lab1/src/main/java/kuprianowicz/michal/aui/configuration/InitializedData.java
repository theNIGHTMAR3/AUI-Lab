package kuprianowicz.michal.aui.configuration;

import kuprianowicz.michal.aui.character.entity.Book;
import kuprianowicz.michal.aui.character.entity.Library;
import kuprianowicz.michal.aui.character.service.BookService;
import kuprianowicz.michal.aui.character.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        Book LOTR= Book.builder()
                .Id(1L)
                .title("The Lord of the Rings")
                .author("J.R.R. Tolkien")
                .year(1968)
                .genre("fantasy")
                .build();

        Book hobbit= Book.builder()
                .Id(2L)
                .title("The Hobbit")
                .author("J.R.R. Tolkien")
                .year(1937)
                .genre("fantasy")
                .build();

        Book year1984= Book.builder()
                .Id(3L)
                .title("Nineteen Eighty-Four")
                .author("George Orwell")
                .year(1949)
                .genre("fiction")
                .build();

        Book potop= Book.builder()
                .Id(4L)
                .title("Potop")
                .author("Henryk Sienkiewicz")
                .year(1886)
                .genre("historical")
                .build();

        bookService.create(LOTR);
        bookService.create(hobbit);
        bookService.create(year1984);
        bookService.create(potop);


        Library empik= Library.builder()
                .Id(1L)
                .name("empik")
                .address("Niepodleglosci 130")
                .establishedYear(1990)
                .books(Arrays.asList(LOTR,hobbit,year1984))
                .build();

        Library ateneum= Library.builder()
                .Id(1L)
                .name("Ateneum")
                .address("3 Maja")
                .establishedYear(2005)
                .books(Arrays.asList(hobbit,year1984,potop))
                .build();

        libraryService.create(empik);
        libraryService.create(ateneum);

    }


}
