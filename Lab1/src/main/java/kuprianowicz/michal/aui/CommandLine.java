package kuprianowicz.michal.aui;

import kuprianowicz.michal.aui.character.entity.Book;
import kuprianowicz.michal.aui.character.entity.Library;
import kuprianowicz.michal.aui.character.service.BookService;
import kuprianowicz.michal.aui.character.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

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

        StartMenu();

        //libraryService.findAll().forEach(System.out::println);
    }


    private void StartMenu(){
        Scanner scanner=new Scanner(System.in);

        int option = -1;
        while (option != 0)
        {
            PrintMenu();

            option=scanner.nextInt();

            switch (option) {
                case 1:
                    PrintAllBooks();
                    break;
                case 2:
                    PrintAllLibraries();
                    break;
                case 3:
                    PrintAllBooksWithAuthor();
                    break;
                case 4:
                    DeleteBookWithId();
                    break;
                case 5:
                    DeleteLibraryWithId();
                    break;
                case 6:
                    AddBook();
                    break;
                case 7:
                    AddLibrary();
                    break;

                case 0:
                    System.out.println("Exiting the program");
                    break;
                default:
                    System.out.println("Sorry, please enter valid Option");
            }
        }
    }


    private void PrintMenu(){

        System.out.println("------MENU------");
        System.out.println("1 - find all Books");
        System.out.println("2 - find all Libraries");
        System.out.println("3 - find books from given author");
        System.out.println("4 - delete Book with given ID");
        System.out.println("5 - delete Library with given ID");
        System.out.println("6 - add new Book");
        System.out.println("7 - add new Library ");
        System.out.println("0 - quit");
        System.out.println("---------------");
    }


    private void PrintAllBooks(){
        System.out.println(bookService.findAll());
    }

    private void PrintAllLibraries(){
        libraryService.findAll().forEach(System.out::println);

        //System.out.println(libraryService.findAll());
    }

    private void PrintAllBooksWithAuthor(){
        System.out.println("Type author");
        Scanner scanner =new Scanner(System.in);
        String author= scanner.nextLine();

        List<Book> result=bookService.findAllWithAuthor(author);
        if(!result.isEmpty()){
            System.out.println(result);
        }
        else{
            System.out.println("No books found");
        }
    }

    private void DeleteBookWithId(){
        System.out.println("Type book ID");
        Scanner scanner =new Scanner(System.in);
        Long id= scanner.nextLong();

        bookService.delete(id);
    }

    private void DeleteLibraryWithId(){
        System.out.println("Type library ID");
        Scanner scanner =new Scanner(System.in);
        Long id= scanner.nextLong();

        libraryService.delete(id);
    }

    private void AddBook(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Type book ID");
        Long id= parseLong(scanner.nextLine());

        System.out.println("Type book title");
        String title= scanner.nextLine();

        System.out.println("Type book author");
        String author= scanner.nextLine();

        System.out.println("Type book year");
        int year= parseInt(scanner.nextLine());

        System.out.println("Type book genre");
        String genre= scanner.nextLine();

        Book newBook=Book.builder()
                .Id(id)
                .title(title)
                .author(author)
                .year(year)
                .genre(genre)
                .build();

        bookService.create(newBook);
    }

    private void AddLibrary(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Type library ID");
        Long id= parseLong(scanner.nextLine());

        System.out.println("Type library name");
        String name= scanner.nextLine();

        System.out.println("Type library address");
        String address= scanner.nextLine();

        System.out.println("Type library year");
        int year= parseInt(scanner.nextLine());


        System.out.println("How many books you want to add?");
        int amount=parseInt(scanner.nextLine());
        List <Book> booksToAdd=new ArrayList<>();

        for(int i=0;i<amount;i++){
            System.out.println("Type books to be added");

            if(libraryService.find(parseLong(scanner.nextLine())).isPresent())
            {
                Book result=bookService.find(parseLong(scanner.nextLine())).get();
                booksToAdd.add(result);
            }


        }


        Library newLibrary=Library.builder()
                .Id(id)
                .name(name)
                .address(address)
                .establishedYear(year)
                .books(booksToAdd)
                .build();

        libraryService.create(newLibrary);
    }
}
