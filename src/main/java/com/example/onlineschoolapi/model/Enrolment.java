package com.example.onlineschoolapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "enrolment")
@Entity(name = "Enrolment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Enrolment implements Comparable<Enrolment> {

    @Id

    @SequenceGenerator(
            name = "enrol_sequence",
            sequenceName = "enrol_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "enrol_sequence"
    )
    @Column(
            name = "id"
    )
    @EqualsAndHashCode.Exclude
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_id_fk"))
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "course_id_fk"))
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonBackReference
    private Course course;

    @Column(
            name = "create_at",
            nullable = false,
            columnDefinition = "DATE"
    )
    @NotNull
    private LocalDate createAt;

    @Override

    public boolean equals(Object o){
        Enrolment er=(Enrolment) o;
        return this.getStudent().getId()==er.getStudent().getId() && this.getCourse().getId()==er.getCourse().getId();
    }

    @Override
    public int compareTo(Enrolment o) {
//        if (this.getStudent().getId() > o.getStudent().getId() && this.getCourse().getId() > o.getCourse().getId()) {
//            return 1;
//        } else if (this.getStudent().getId() < o.getStudent().getId() && this.getCourse().getId() < o.getCourse().getId()) {
//            return -1;
//        } else {
//            return 0;
//        }

        if(this.equals(o)){
            return 0;
        }else if(o.getId()==null){
            return -1;
        }else{
            return 1;
        }
    }


}
