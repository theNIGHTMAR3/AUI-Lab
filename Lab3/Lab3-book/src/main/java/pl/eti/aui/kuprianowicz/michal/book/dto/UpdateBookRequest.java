package pl.eti.aui.kuprianowicz.michal.book.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;

import java.util.function.BiFunction;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateBookRequest {

    private String title;

    private String author;

    private int year;

    private String genre;

    public static BiFunction<Book, UpdateBookRequest, Book> dtoToEntityUpdater() {
        return (book, request) -> {
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setYear(request.getYear());
            book.setGenre(request.getGenre());
            return book;
        };
    }
}
