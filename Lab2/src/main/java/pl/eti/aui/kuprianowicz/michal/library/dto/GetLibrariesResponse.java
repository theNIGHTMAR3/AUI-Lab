package pl.eti.aui.kuprianowicz.michal.library.dto;

import lombok.*;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;

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
public class GetLibrariesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Library {
        private Long Id;

        private String name;

        private String address;
    }
    @Singular
    private List<Library> libraries;

    // returns mapper for converting entity to dto
    public static Function<Collection<pl.eti.aui.kuprianowicz.michal.library.entity.Library>, GetLibrariesResponse> entityToDtoMapper() {
        return teachers -> {
            GetLibrariesResponse.GetLibrariesResponseBuilder response = GetLibrariesResponse.builder();
            teachers.stream()
                    .map(library -> Library.builder()
                            .Id(library.getId())
                            .name(library.getName())
                            .address(library.getAddress())
                            .build())
                    .forEach(response::library);
            return response.build();
        };
    }


}
