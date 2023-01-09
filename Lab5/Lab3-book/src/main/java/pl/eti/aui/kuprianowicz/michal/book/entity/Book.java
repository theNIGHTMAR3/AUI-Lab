package pl.eti.aui.kuprianowicz.michal.book.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.eti.aui.kuprianowicz.michal.library.entity.Library;


import java.io.Serializable;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String title;
    
    private String author;

    private int year;
    
    private String genre;

    @ManyToOne
    @JoinColumn(name = "library")
    private Library library;

    @Override
    public String toString(){
        return "\nBook{ "+
                "Id = "+ getId()+
                ", title = "+ getTitle()+
                ", author = "+ getAuthor()+
                ", year = "+ getYear()+
                ", genre = "+ getGenre()+" }";
    }
}
