package com.example.onlineschoolapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name="student")
@Entity(name="Student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Student implements Comparable<Student> {


    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator="student_sequence"
    )

    @Column(
            name="id"
    )
    private Long id;



    @NotEmpty
    @Size(min=3,max=10,message = "Numarul minim de litere este 3 ! ")
    @Column(

            name="first_name",
            nullable=false,
            columnDefinition="TEXT"
    )
    private String firstName;


    @NotEmpty
    @Size(min=3,max=10,message = "Numarul minim de litere este 3 !")

    @Column(

            name="last_name",
            nullable=false,
            columnDefinition="TEXT"
    )
    private String lastName;

    @Column
            (
                    name="email",
                    nullable = false,
                    columnDefinition = "TEXT"
            )

    @NotEmpty
    @Size(min=8,message = "Emailul trebuie sa contina minim 8 caractere")
    private String email;


    @Column(
            name="age",
            nullable=false,
            columnDefinition = "INTEGER"
    )

    @NotEmpty
    @Min(value=18,message = "Studentul trebuie sa aibe minim 18 ani ! ")
    private int age ;


    @Override
    public String toString(){
        String text = "ID : "+this.id+"\n";
        text+="First Name : "+this.firstName+"\n";
        text+="Last Name : "+this.lastName+"\n";
        text+="Email : "+this.email+"\n";
        text+="Age : "+this.age+"\n";

        return text;
    }




    @OneToMany(mappedBy = "student",
            cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Book> bookList=new ArrayList<>();


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Enrolment> enrolemntsList=new ArrayList<>();

//     public void initLazyCollection(){
//
//         this.enrolemntsList.size();
//         this.bookList.size();
//     }


    public void addBook(Book book){

        bookList.add(book);
        book.setStudent(this);

    }


    public void addEnrol(Enrolment enrolment){
        enrolemntsList.add(enrolment);
        enrolment.setStudent(this);
    }








    @Override
    public int compareTo(Student o) {
        if(this.age>o.getAge()){
            return 1;
        }else if(this.age<o.getAge()){
            return -1;
        }else {
            return 0;
        }
    }
}
