package ro.mycode.onlineschoolapi.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import ro.mycode.onlineschoolapi.dto.*;
import ro.mycode.onlineschoolapi.jwt.JWTTokenProvider;
import ro.mycode.onlineschoolapi.model.Course;
import ro.mycode.onlineschoolapi.model.Student;
import ro.mycode.onlineschoolapi.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.OK;

import static ro.mycode.onlineschoolapi.constante.Utils.JWT_TOKEN_HEADER;

@RestController
@RequestMapping("api/v1/students")
public class StudentRest {

    private StudentService studentService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody StudentDTO user) {
        authenticate(user.email(), user.password());
        Student loginUser = studentService.findStudentByEmail(user.email());
        Student userPrincipal = new Student(loginUser.getFirstName(), loginUser.getSecondName(),loginUser.getEmail(), loginUser.getAge(),loginUser.getPassword());
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        LoginResponse loginResponse= new LoginResponse(userPrincipal.getId(),user.email(),jwtHeader.getFirst(JWT_TOKEN_HEADER));
        return new ResponseEntity<>(loginResponse, jwtHeader, OK);
    }

    @GetMapping("/")
    public ResponseEntity<StudentListResponse> studentList() {
        StudentListResponse studentListResponse = StudentListResponse.builder().studentList(studentService.getAllStudents()).message("All students").build();
        return new ResponseEntity<>(studentListResponse, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<CreateBookRequest> addBookToAStudent(@RequestBody CreateBookRequest createBookRequest) {
        studentService.addBook(createBookRequest);
        return new ResponseEntity<>(createBookRequest, HttpStatus.CREATED);
    }


    @PostMapping("/enrollCourseToAStudent")
    public ResponseEntity<EnrollRequestStudentToCourse> addCourseToAStudent(@RequestBody EnrollRequestStudentToCourse enrollRequestStudentToCourse) {
        studentService.addCourse(enrollRequestStudentToCourse);
        return new ResponseEntity<>(enrollRequestStudentToCourse, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancelCourse")
    public ResponseEntity<EnrollRequestStudentToCourse> cancelCourseToAStudent(@RequestBody EnrollRequestStudentToCourse enrollRequestStudentToCourse) {
        studentService.removeEnrolment(enrollRequestStudentToCourse);
        return new ResponseEntity<>(enrollRequestStudentToCourse, HttpStatus.OK);
    }

    @GetMapping("/bestCourse")
    public ResponseEntity<Course> bestCourse() {
        Course course = studentService.bestCourse();
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/cancelBook")
    public ResponseEntity<CreateBookRequest> cancelBookToAStudent(@RequestBody CreateBookRequest createBookRequest){
        studentService.removeBook(createBookRequest);
        return new ResponseEntity<>(createBookRequest,HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getJwtHeader(Student user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJWTToken((UserDetails) user));
        return headers;
    }

}







