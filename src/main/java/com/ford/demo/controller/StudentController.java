package com.ford.demo.controller;

import com.ford.demo.dto.StudentDto;
import com.ford.demo.entity.Student;
import com.ford.demo.service.IStudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping("/add-student")
    public StudentDto addStudentInfo(@RequestBody @Valid StudentDto studentDto, BindingResult bindingResult) {
        log.info(this.getClass().getName() + "::addStudentInfo(student) method begins");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("error is:" + error.getDefaultMessage()));
            return null;
        }
        StudentDto savedStudentDto = studentService.saveStudent(studentDto);
        log.info(this.getClass().getName() + "::addStudentInfo(student) method ends");
        return savedStudentDto;
    }

    @GetMapping("/find-student")
    public StudentDto getStudentInfo(@RequestParam("sId") Integer studentId) {

        log.info(this.getClass().getName() + "::getStudentInfo(studentId) method begins");
        StudentDto studentDto = studentService.findStudent(studentId);
        log.info(this.getClass().getName() + "::getStudentInfo(studentId) method ends");
        return studentDto;
    }
}