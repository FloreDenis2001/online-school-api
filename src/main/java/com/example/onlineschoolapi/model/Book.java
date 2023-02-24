package com.example.onlineschoolapi.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.DecimalMin;
//import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Table(name="book")
@Entity(name="Book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book {
    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "books_sequence"
    )
    private Long id;

    @Column(
            name="title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @Size(min = 2, message = "min title length should be 2")
    String title;

    @Size(min = 2, message = "min author length should be 2")
    @Column( name="author",
            nullable = false)
    String author;

    @Column( name="price",
            nullable = false)
    @DecimalMin(value = "0.1",message = "Price should have a value.")
    double price;

    @Column( name="stars")
    Long stars;

    @ManyToOne
//            (
//            fetch = FetchType.LAZY
//    )
    @JoinColumn(
            name="user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="user_id_fk")

    )
    @JsonBackReference
    private Student student;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", stars=" + stars +
                '}';
    }
}
