package kuprianowicz.michal.aui.character.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
public class Library {

    private Long Id;

    private String name;

    private String address;

    private int establishedYear;

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
