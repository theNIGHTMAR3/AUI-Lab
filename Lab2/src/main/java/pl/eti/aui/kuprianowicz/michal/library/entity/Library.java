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

@EqualsAndHashCode
@Entity
@Table(name = "libraries")
public class Library implements Serializable {

    @Id
    private long Id;

    private String name;

    private String address;

    private int establishedYear;

    @Override
    public String toString()
    {
        return "Library{ "+
                "Id = "+ getId()+
                ", name = "+ getName()+
                ", address = "+ getAddress()+
                ", established year = "+ getEstablishedYear()+" }\n";
    }
}
