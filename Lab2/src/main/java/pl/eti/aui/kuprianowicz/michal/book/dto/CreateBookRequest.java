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
public class CreateBookRequest {

    private Long Id;

    private String title;

    private String author;

    private int year;

    private String genre;

    public static Function<CreateBookRequest, Book> dtoToEntityMapper()
    {
        return request -> Book.builder()
                .Id(request.getId())
                .title(request.getTitle())
                .author(request.getAuthor())
                .year(request.getYear())
                .genre(request.getGenre())
                .build();
    }
}
