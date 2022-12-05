package pl.eti.aui.kuprianowicz.michal.library.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import pl.eti.aui.kuprianowicz.michal.library.event.dto.CreateLibraryRequest;

@Repository
public class LibraryEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public LibraryEventRepository(@Value("${books.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Library library) {
        restTemplate.delete("/libraries/{libraryId}", library.getLibraryId());
    }

    public void create(Library library) {
        restTemplate.postForLocation("/libraries", CreateLibraryRequest.entityToDtoMapper().apply(library));
    }
}
