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
public class GetLibraryResponse {

    private Long Id;

    private String name;

    private String address;

    private int establishedYear;

    public static Function<Library, GetLibraryResponse> entityToDtoMapper() {
        return library -> GetLibraryResponse.builder()
                .Id(library.getId())
                .name(library.getName())
                .address(library.getAddress())
                .establishedYear(library.getEstablishedYear())
                .build();

    }
}
