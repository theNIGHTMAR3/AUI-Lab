package pl.eti.aui.kuprianowicz.michal.library.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

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
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @GeneratedValue
    private long libraryId;

    private String name;

    private String address;

    private int establishedYear;

    @Override
    public String toString()
    {
        return "Library{ "+
                "Id = "+ this.getLibraryId()+
                ", name = "+ getName()+
                ", address = "+ getAddress()+
                ", established year = "+ getEstablishedYear()+" }\n";
    }
}
