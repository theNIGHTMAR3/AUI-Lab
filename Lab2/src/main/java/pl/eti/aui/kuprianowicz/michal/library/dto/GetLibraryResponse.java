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

    private long id;

    private String name;

    private String address;

    private int establishedYear;


    public static Function<Library, GetLibraryResponse> entityToDtoMapper() {
        return user -> GetLibraryResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .establishedYear(user.getEstablishedYear())
                .build();
    }

}