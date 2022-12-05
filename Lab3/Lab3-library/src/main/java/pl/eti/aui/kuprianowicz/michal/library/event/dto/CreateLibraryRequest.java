package pl.eti.aui.kuprianowicz.michal.library.event.dto;

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

    public static Function<Library, CreateLibraryRequest> entityToDtoMapper() {
        return entity -> CreateLibraryRequest.builder()
                .libraryId(entity.getLibraryId())
                .build();
    }
}
