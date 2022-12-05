package pl.eti.aui.kuprianowicz.michal.library.dto;

import lombok.*;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateLibraryRequest {

    private String name;

    private String address;

    private int establishedYear;


    public static BiFunction<Library, UpdateLibraryRequest, Library> dtoToEntityUpdater() {
        return (library, request) -> {
            library.setName(request.getName());
            library.setAddress(request.getAddress());
            library.setEstablishedYear(request.getEstablishedYear());
            return library;
        };
    }
}
