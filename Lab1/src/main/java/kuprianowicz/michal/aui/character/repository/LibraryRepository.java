package kuprianowicz.michal.aui.character.repository;

import kuprianowicz.michal.aui.character.entity.Book;
import kuprianowicz.michal.aui.character.entity.Library;
import kuprianowicz.michal.aui.datastore.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LibraryRepository {

    private DataStore store;

    @Autowired
    public LibraryRepository(DataStore store) {
        this.store=store;
    }


    public Optional<Library> find(Long id) {
        return store.findLibraryById(id);
    }


    public List<Library> findAll() {
        return store.findAllLibraries();
    }

    public void create(Library entity) {
        store.createLibraryFromOtherLibrary(entity);
    }


    public void delete(Library entity) {
        store.deleteLibrary(entity.getId());
    }


    public void update(Library entity) {
        store.updateLibrary(entity);
    }


    public Optional<Library> findByIdAndName(Long id, String name) {
        return store.getLibraryStream()
                .filter(library -> library.getName().equals(name))
                .filter(library -> library.getId().equals(id))
                .findFirst();
    }


    public List<Library> findAllByName(String name) {
        return store.getLibraryStream()
                .filter(library -> library.getName().equals(name))
                .collect(Collectors.toList());
    }
}
