package pl.eti.aui.kuprianowicz.michal.library.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "libraries")
public class Library implements Serializable {

    @Id
    private long libraryId;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "library")
    @ToString.Exclude
    private List<Book> containsBooks;


}
