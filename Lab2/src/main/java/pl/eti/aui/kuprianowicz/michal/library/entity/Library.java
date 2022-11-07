package pl.eti.aui.kuprianowicz.michal.library.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.eti.aui.kuprianowicz.michal.book.entity.Book;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode

@Entity
@Table(name = "libraries")
public class Library implements Serializable {

    @javax.persistence.Id
    private Long Id;

    private String name;

    private String address;

    private int establishedYear;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

    @Override
    public String toString()
    {
        return "Library{ "+
                "Id = "+ getId()+
                ", name = "+ getName()+
                ", address = "+ getAddress()+
                ", established year = "+ getEstablishedYear()+
                ", books="+ getBooks()+" }\n";
    }
}
