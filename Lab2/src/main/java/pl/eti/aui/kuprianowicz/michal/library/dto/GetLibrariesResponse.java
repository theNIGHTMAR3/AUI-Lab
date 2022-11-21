package pl.eti.aui.kuprianowicz.michal.library.dto;

import lombok.*;

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

        private long libraryId;

        private String name;

    }


    @Singular
    private List<Library> libraries;


    public static Function<Collection<pl.eti.aui.kuprianowicz.michal.library.entity.Library>, GetLibrariesResponse> entityToDtoMapper() {
        return libraries -> {
            GetLibrariesResponse.GetLibrariesResponseBuilder response = GetLibrariesResponse.builder();
            libraries.stream()
                    .map(user -> Library.builder()
                            .libraryId(user.getLibraryId())
                            .name(user.getName())
                            .build())
                    .forEach(response::library);
            return response.build();
        };
    }

}
