package pl.eti.aui.kuprianowicz.michal.library.dto;


import lombok.*;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateLibraryRequest {


    private long libraryId;

    private String name;

    private String address;

    private int establishedYear;



    public static Function<CreateLibraryRequest, Library> dtoToEntityMapper() {
        return request -> Library.builder()
                .libraryId(request.getLibraryId())
                .name(request.getName())
                .address(request.getAddress())
                .establishedYear(request.getEstablishedYear())
                .build();
    }

}
