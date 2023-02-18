package com.example.onlineschoolapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name="enrolment")
@Entity(name="Enrolment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Enrolemnt {

    @Id

    @SequenceGenerator(
            name="enrol_sequence",
            sequenceName = "enrol_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "enrol_sequence"
    )
    @Column(
            name="id"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id",foreignKey = @ForeignKey(name="student_id_fk"))
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "id",foreignKey = @ForeignKey(name="course_id_fk"))

    private Course course;

    @Column(
            name = "create_at",
            nullable = false,
            columnDefinition = "DATE"
    )
    @NotEmpty
    private LocalDate createAt;
}
