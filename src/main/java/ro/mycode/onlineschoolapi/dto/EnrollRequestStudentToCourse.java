package ro.mycode.onlineschoolapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollRequestStudentToCourse {

    @NotNull
    private Long idStudent;

    @NotNull
    private Long idCourse;


}
