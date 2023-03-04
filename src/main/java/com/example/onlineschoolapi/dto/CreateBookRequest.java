package com.example.onlineschoolapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    @NotNull
    private Long idBook;
    @NotNull
    private Long idStudent;
    @NotEmpty
    @Size(min = 3, message = "The book has to have minim 3 characters")
    private String title;
    @Size(min = 2, message = "min author length should be 2")
    String author;
    @DecimalMin(value = "0.1", message = "Price should have a value.")
    double price;
    @NotNull
    private Long stars;


}
