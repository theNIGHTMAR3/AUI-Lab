package kuprianowicz.michal.aui.datastore;

import kuprianowicz.michal.aui.character.entity.Book;
import kuprianowicz.michal.aui.character.entity.Library;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Log
@Component
public class DataStore {

    //Set of all available books
    private Set<Book> books=new HashSet<>();

    //Set of all available libraries
    private Set<Library> libraries=new HashSet<>();

    // find all books, can return empty list
    public synchronized List<Book> findAllBooks() {
        return new ArrayList<>(books);
    }

    // find all libraries, can return empty list
    public synchronized List<Library> findAllLibraries() {
        return new ArrayList<>(libraries);
    }

    // find book with given title
    public Optional<Book> findBook(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst();
    }

    // find book with given id
    public Optional<Book> findBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    // find library with given name
    public Optional<Library> findLibrary(String name) {
        return libraries.stream()
                .filter(library -> library.getName().equals(name))
                .findFirst();
    }

    // find library with given id
    public Optional<Library> findLibraryById(Long id) {
        return libraries.stream()
                .filter(library -> library.getId().equals(id))
                .findFirst();
    }

    // creates new book
    public synchronized void createBook(Book book) throws IllegalArgumentException {
        findBook(book.getTitle()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The book title \"%s\" is not unique", book.getTitle()));
                },
                () -> books.add(book));
    }

    // creates book from existing book
    public synchronized void createBookFromOtherBook(Book book) throws IllegalArgumentException {
        book.setId(findAllBooks().stream().mapToLong(Book::getId).max().orElse(0) + 1);
        books.add(book);
    }

    // creates library from existing library
    public synchronized void createLibraryFromOtherLibrary(Library library) throws IllegalArgumentException {
        library.setId(findAllLibraries().stream().mapToLong(Library::getId).max().orElse(0) + 1);
        libraries.add(library);
    }

    // creates new library
    public synchronized void createLibrary(Library library) throws IllegalArgumentException {
        findLibrary(library.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The library name \"%s\" is not unique", library.getName()));
                },
                () -> libraries.add(library));
    }

    // update book
    public synchronized void updateBook(Book book) throws IllegalArgumentException {
        findBookById(book.getId()).ifPresentOrElse(
                original -> {
                    books.remove(original);
                    books.add(book);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The book with id \"%d\" does not exist", book.getId()));
                });
    }

    // update library
    public synchronized void updateLibrary(Library library) throws IllegalArgumentException {
        findLibraryById(library.getId()).ifPresentOrElse(
                original -> {
                    libraries.remove(original);
                    libraries.add(library);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The library with id \"%d\" does not exist", library.getId()));
                });
    }

    // deletes book with given id
    public synchronized void deleteBook(Long id) throws IllegalArgumentException {
        findBookById(id).ifPresentOrElse(
                original -> books.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The book with id \"%d\" does not exist", id));
                });
    }


    // deletes library with given id
    public synchronized void deleteLibrary(Long id) throws IllegalArgumentException {
        findLibraryById(id).ifPresentOrElse(
                original -> libraries.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The library with id \"%d\" does not exist", id));
                });
    }

    public Stream<Book> getBookStream() {
        return books.stream();
    }

    public Stream<Library> getLibraryStream() {
        return libraries.stream();
    }

}
