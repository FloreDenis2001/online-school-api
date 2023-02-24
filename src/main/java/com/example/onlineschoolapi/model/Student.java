package com.example.onlineschoolapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.DecimalMax;
//import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;


@Table(name="student")
@Entity(name="Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator =  "student_sequence")
    Long id;

    @Column(name = "first_name",
            nullable = false)
    String firstName;

    @Column(name = "second_name",
            nullable = false)
    String secondName;

    @Column(name = "email",
            nullable = false)
    @Email
    String email;

    @DecimalMax(value = "25", message = "Cursul este alocat persoanelor sub 25 de ani")
    @Column(name = "age",
            nullable = false)
    double age;


    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    List<Book> books= new ArrayList<>();

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )

    @JsonManagedReference
    List<Enrolment> enrolments = new ArrayList<>();

    public void addBook(Book book){
        this.books.add(book);
        book.setStudent(this);
    }


    public void addEnrolment(Enrolment enrolment){
        this.enrolments.add(enrolment);
        enrolment.setStudent(this);
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
