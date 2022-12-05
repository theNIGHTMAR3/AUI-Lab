package pl.eti.aui.kuprianowicz.michal.library.service;

import pl.eti.aui.kuprianowicz.michal.library.entity.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eti.aui.kuprianowicz.michal.library.event.repository.LibraryEventRepository;
import pl.eti.aui.kuprianowicz.michal.library.repository.LibraryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private LibraryRepository repository;
    private LibraryEventRepository eventRepository;

    @Autowired
    public LibraryService(LibraryRepository repo,LibraryEventRepository eventRepository){
        this.repository=repo;
        this.eventRepository=eventRepository;
    }

    public Optional<Library> find(Long id) {
        return repository.findById(id);
    }

    public List<Library> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Library library) {
        repository.save(library);
        eventRepository.create(library);
    }

    @Transactional
    public void update(Library library) {
        repository.save(library);
        eventRepository.create(library);
    }

    @Transactional
    public void delete(Long libraryId) {
        eventRepository.delete(repository.findById(libraryId).get());
        repository.deleteById(libraryId);

    }
}
