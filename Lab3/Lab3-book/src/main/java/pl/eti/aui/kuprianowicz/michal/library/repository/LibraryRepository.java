package pl.eti.aui.kuprianowicz.michal.library.repository;

import pl.eti.aui.kuprianowicz.michal.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Optional<Library> findById(Long id);

}
