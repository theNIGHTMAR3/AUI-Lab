package kuprianowicz.michal.aui;

import kuprianowicz.michal.aui.character.service.BookService;
import kuprianowicz.michal.aui.character.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private LibraryService libraryService;
    private BookService bookService;

    @Autowired
    public CommandLine(LibraryService libraryService,BookService bookService)
    {
        this.libraryService=libraryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception{

        System.out.println();
        System.out.println();

        startMenu();

        libraryService.findAll().forEach(System.out::println);
    }


    private void startMenu(){
        Scanner scanner=new Scanner(System.in);
        String option="";

        int i=0;

        while(!option.equals("quit")){
            System.out.println("\n type correct command, 'quit' to exit the program\n");
            PrintMenu();
        }
    }




    private void PrintMenu(){
        System.out.println("------MENU------\n" +
                "findBooks\n" +
                "findLibraries\n" +
                "deleteBook\n" +
                "deleteLibrary\n" +
                "quit\n" +
                "---------------\n");
    }

}
