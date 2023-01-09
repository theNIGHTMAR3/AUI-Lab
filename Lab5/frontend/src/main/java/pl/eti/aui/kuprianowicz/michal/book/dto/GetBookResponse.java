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


import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBookResponse {


    private Long id;


    private String title;


    private String author;


    private int year;


    private String genre;


    public static Function<Book, GetBookResponse> entityToDtoMapper() {
        return book -> GetBookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .year(book.getYear())
                .genre(book.getGenre())
                .build();
    }

}
