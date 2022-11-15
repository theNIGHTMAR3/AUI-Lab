package pl.eti.aui.kuprianowicz.michal.library.dto;


import lombok.*;
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
public class CreateLibraryRequest {


    private Long id;


    private String name;


    private String address;


    private int establishedYear;



    public static Function<CreateLibraryRequest, Library> dtoToEntityMapper(
            Supplier<Book> characterSupplier) {
        return request -> Library.builder()
                .id(request.getId())
                .name(request.getName())
                .address(request.getAddress())
                .establishedYear(request.getEstablishedYear())
                .build();
    }

}