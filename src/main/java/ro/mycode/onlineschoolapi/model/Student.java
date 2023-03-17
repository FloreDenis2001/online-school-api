package ro.mycode.onlineschoolapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;


@Table(name = "student")
@Entity(name = "Student")
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
            generator = "student_sequence")
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
            cascade = CascadeType.ALL

    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
            @Builder.Default
    List<Book> books = new ArrayList<>();


    public void addBook(Book book) {
        this.books.add(book);
        book.setStudent(this);
    }

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "enrolled_coruse", joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    @Builder.Default
    private List<Course> enrolledCourses=new ArrayList<>();

    public void addCourse(Course course){
        enrolledCourses.add(course);
    }

    public void removeCourse(Course course){
        enrolledCourses.remove(course);
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


    public boolean vfExistsBook(Book book) {
        return this.books.contains(book);
    }

    public boolean vfExistCourse(Course course){
        for (Course x : enrolledCourses){
                if(x.getId()==course.getId()){
                  return true;
            }
        }
        return false;
    }

}
