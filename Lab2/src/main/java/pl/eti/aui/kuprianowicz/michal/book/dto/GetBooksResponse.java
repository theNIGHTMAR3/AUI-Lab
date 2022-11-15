/*
package pl.eti.aui.kuprianowicz.michal.book.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;


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

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Book {

        private Long id;

        private String title;

    }
    @Singular
    private List<Book> books;

    public static Function<Collection<pl.eti.aui.kuprianowicz.michal.book.entity.Book>, GetBooksResponse> entityToDtoMapper() {
        return books -> {
            GetBooksResponseBuilder response = GetBooksResponse.builder();
            books.stream()
                    .map(book -> Book.builder()
                            .id(book.getId())
                            .title(book.getTitle())
                            .build())
                    .forEach(response::book);
            return response.build();
        };
    }
}
*/
