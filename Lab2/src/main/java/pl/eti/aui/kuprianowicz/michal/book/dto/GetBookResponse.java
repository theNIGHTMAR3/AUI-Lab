package pl.eti.aui.kuprianowicz.michal.book.dto;


import lombok.*;
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

    private Long Id;

    private String title;

    private String author;

    private int year;

    private String genre;

    public static Function<Book, GetBookResponse> entityToDtoMapper()
    {
        return book -> GetBookResponse.builder()
                .Id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .year(book.getYear())
                .genre(book.getGenre())
                .build();
    }
}
