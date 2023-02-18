package com.example.onlineschoolapi.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonManagedReference
    private List<Enrolemnt> enrolemntsList=new ArrayList<>();
    @Override
    public boolean equals(Object o){
        return this.departament.compareTo(((Course)o).getDepartament())==0;
    }
}
