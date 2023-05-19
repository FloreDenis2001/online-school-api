package ro.mycode.onlineschoolapi.dto;

public record RegisterResponse(Long studentId,String firstName,String secondName,String email , double age , String token) {
}
