package pl.eti.aui.kuprianowicz.michal.book.dto;


import lombok.*;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBooksResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Book {
        private Long Id;

        private String title;

        private String author;
    }

    @Singular
    private List<Book> books;

    public static Function<Collection<pl.eti.aui.kuprianowicz.michal.book.entity.Book>, GetBooksResponse> entityToDtoMapper() {
        return books -> {
            GetBooksResponseBuilder response = GetBooksResponse.builder();
            books.stream()
                    .map(book -> Book.builder()
                            .Id(book.getId())
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .build())
                    .forEach(response::book);
            return response.build();

        };
    }
}
