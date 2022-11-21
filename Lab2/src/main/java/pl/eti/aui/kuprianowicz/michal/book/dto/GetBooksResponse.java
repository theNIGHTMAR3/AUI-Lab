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


    public static class BookEntry {
        private Long id;
        private String title;
    }

    @Singular
    private List<BookEntry> books;

    public static Function<Collection<Book>, GetBooksResponse> entityToDtoMapper() {
        return books -> {
            GetBooksResponseBuilder response = GetBooksResponse.builder();
            books.stream()
                    .map(book -> BookEntry.builder()
                            .id(book.getId())
                            .title(book.getTitle())
                            .build())
                    .forEach(response::book);
            return response.build();
        };
    }
}


