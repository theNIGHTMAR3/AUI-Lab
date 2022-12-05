package pl.eti.aui.kuprianowicz.michal.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import pl.eti.aui.kuprianowicz.michal.library.service.LibraryService;

import javax.annotation.PostConstruct;


@Component
public class InitializedData {

    private final LibraryService libraryService;

    @Autowired
    public InitializedData(LibraryService libraryService){
        this.libraryService=libraryService;
    }

    @PostConstruct
    private synchronized void init()
    {

        Library empik= Library.builder()
                .libraryId(1L)
                .name("empik")
                .address("Niepodleglosci 130")
                .establishedYear(1990)
                .build();

        Library ateneum= Library.builder()
                .libraryId(2L)
                .name("Ateneum")
                .address("3 Maja")
                .establishedYear(2005)
                .build();

        libraryService.create(empik);
        libraryService.create(ateneum);


    }


}
