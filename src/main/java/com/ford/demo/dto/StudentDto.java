package com.ford.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    @NotNull
    @Min(value = 1000,message = "min value for id is 1000")
    @Max(value = 2000, message = "max value for id is 2000")
    private int id;

    @NotEmpty(message = "Name must not be Empty")
    private String name;

    @Min(value=12)
    @Max(value = 35)
    private int age;
    @NotNull(message = "Branch must be not null")
    @NotEmpty(message = "Branch must not be empty")
    private String branch;
    @Min(value=10000,message = "Min fee is 10k")
    private double fee;

    @NotNull(message = "dateOfBirth is mandatory")
    private LocalDate dateOfBirth;
    @NotEmpty(message = "college must be given")
    private String college;
}
