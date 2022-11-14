/*
package pl.eti.aui.kuprianowicz.michal.library.repository;

import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

*/
/*    Optional<Library> findById(Long id);

    Optional<Library> findByName(String name);*//*



    */
/*private DataStore store;

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
    }*//*

}
*/
