package kuprianowicz.michal.aui.character.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder

@EqualsAndHashCode
public class Book implements Serializable {

    private Long Id;

    private String title;
    
    private String author;

    private int year;
    
    private String genre;

    @Override
    public String toString(){
        return "\nBook{ "+
                "Id = "+ getId()+
                ", title = "+ getTitle()+
                ", author = "+ getAuthor()+
                ", year = "+ getYear()+
                ", genre="+ getGenre()+" }";
    }
}
