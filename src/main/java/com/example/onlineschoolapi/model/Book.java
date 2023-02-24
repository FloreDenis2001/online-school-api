package com.example.onlineschoolapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "book")
@Entity(name = "Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Builder
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequecene",
            sequenceName = "book_sequence",
            allocationSize = 1
    )

    @GeneratedValue
            (
                    strategy = SEQUENCE,
                    generator = "book_sequence"
            )
    @Column(
            name = "id"
    )
    private Long id;


    @Column(
            name = "book_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty
    @Size(min = 3, message = "Titlul carti trebuie sa contina minim 3 caractere ! ")
    private String bookName;


    @Column(
            name = "create_at",
            nullable = false,
            columnDefinition = "DATE"
    )

    @NotNull
    private LocalDate createdAt;


    @Override
    public String toString() {
        String text = "ID :" + this.id + "\n";
        text += "Book Name : " + this.bookName + "\n";
        text += "Created At:  " + this.createdAt + "\n";
        return text;
    }


    @Override
    public boolean equals(Object o){
        return this.bookName.compareTo(((Book)o).getBookName())==0;
    }

    @ManyToOne
    @JoinColumn(name="student_id",referencedColumnName = "id",foreignKey = @ForeignKey(name="student_id_fk"))
    private Student student;

}

