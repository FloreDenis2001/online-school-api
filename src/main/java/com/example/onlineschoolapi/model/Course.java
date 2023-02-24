package com.example.onlineschoolapi.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name="course")
@Entity(name="Course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Course {
    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = SEQUENCE,
            generator = "coruse_sequcence")

    private Long id;

    @Column(
            name="name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty
    @Size(min=3,message = "Numele cursului trebuie sa contina minim 3 caractere ! ")
    private String name;


    @Column(
            name="departament",
            nullable = false,
            columnDefinition ="TEXT"
    )
    @NotEmpty
    @Size(min=3,message = "Numele departamentului trebuie sa contina minim 3 caractere ! ")
    private String departament;


    @Override
    public String toString(){
        String text="Id :"+this.id+"\n";
        text+="Name : "+this.name+"\n";
        text+="Departament : "+this.departament+"\n";
        return text;
    }


    @OneToMany(mappedBy = "course",cascade=CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonManagedReference
    private List<Enrolment> enrolemntsList=new ArrayList<>();

    public void add(Enrolment enrolment){

        enrolemntsList.add(enrolment);
        enrolment.setCourse(this);
    }

}
