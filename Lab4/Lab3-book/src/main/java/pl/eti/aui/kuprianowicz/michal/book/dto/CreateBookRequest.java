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
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;


import java.util.function.Function;
import java.util.function.Supplier;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateBookRequest {

    private String title;


    private String author;


    private int year;


    private String genre;



    public static Function<CreateBookRequest, Book> dtoToEntityMapper(
            Supplier<Library> librarySupplier) {
        return request -> Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .year(request.getYear())
                .genre(request.getGenre())
                .library(librarySupplier.get())
                .build();
    }
}
