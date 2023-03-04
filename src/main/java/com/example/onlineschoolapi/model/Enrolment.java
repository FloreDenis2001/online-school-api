package com.example.onlineschoolapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;


@Table(name = "enrolment")
@Entity(name = "Enrolment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Enrolment {
    @Id
    @SequenceGenerator(
            name = "enrolment_sequence",
            sequenceName = "enrolment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "enrolment_sequence"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_course")
    @JsonBackReference
    private Course course;

    LocalDate created_at;

    public Enrolment(Student student, Course course, LocalDate created_at) {
        this.student = student;
        this.course = course;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "id=" + id +
                ", student=" + student.getFirstName() + " " + student.getSecondName() +
                ", course=" + course.getName() +
                ", created_at=" + created_at +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        Enrolment compareEnrollment = (Enrolment)obj;
        return compareEnrollment.getStudent().getId() == this.getStudent().getId() && compareEnrollment.getCourse().getId() == this.getCourse().getId();
    }

    public Enrolment(Student student, Course course){
        this.student = student;
        this.course = course;
        this.created_at=LocalDate.now();
    }
}
