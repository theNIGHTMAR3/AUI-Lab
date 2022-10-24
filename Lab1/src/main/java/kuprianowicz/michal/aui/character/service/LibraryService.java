package kuprianowicz.michal.aui.character.service;

import kuprianowicz.michal.aui.character.entity.Book;
import kuprianowicz.michal.aui.character.entity.Library;
import kuprianowicz.michal.aui.character.repository.BookRepository;
import kuprianowicz.michal.aui.character.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {


    private LibraryRepository repository;


    @Autowired
    public LibraryService(LibraryRepository repo){
        this.repository=repo;
    }

    public Optional<Library> find(Long id) {
        return repository.find(id);
    }

    public Optional<Library> find(String name, Long id) {
        return repository.findByIdAndName(id, name);
    }

    public List<Library> findAll() {
        return repository.findAll();
    }

    public List<Library> findAllByName(String name) {
        return repository.findAllByName(name);
    }

    public void create(Library library) {
        repository.create(library);
    }

    public void update(Library library) {
        repository.update(library);
    }

    public void delete(Long id) {
        try{
            repository.delete(repository.find(id).orElseThrow());
        }
        catch(Exception e){
            System.out.println( "Library with given ID doesn't exist");
        }

    }
}
