package pl.eti.aui.kuprianowicz.michal.book.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


import java.io.Serializable;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode

@Entity
@Table(name="books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
