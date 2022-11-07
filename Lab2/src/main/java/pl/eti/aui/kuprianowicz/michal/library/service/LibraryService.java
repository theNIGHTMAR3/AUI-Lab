package pl.eti.aui.kuprianowicz.michal.library.service;

import pl.eti.aui.kuprianowicz.michal.library.entity.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eti.aui.kuprianowicz.michal.library.repository.LibraryRepository;

import javax.transaction.Transactional;
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
        return repository.findById(id);
    }

    public Optional<Library> find(String name) {
        return repository.findByName(name);
    }

    public List<Library> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Library create(Library library) {
        return repository.save(library);
    }

    @Transactional
    public void update(Library library) {
        repository.save(library);
    }

    @Transactional
    public void delete(Long id) {
        try{
            repository.delete(repository.findById(id).orElseThrow());
        }
        catch(Exception e){
            System.out.println( "Library with given ID doesn't exist");
        }

    }
}
