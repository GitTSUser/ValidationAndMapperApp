package com.ford.demo.service;

import com.ford.demo.dto.StudentDto;
import com.ford.demo.entity.Student;
import com.ford.demo.repository.IStudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private IStudentRepository studentRepository;  //creation of mock object

    @InjectMocks
    StudentServiceImpl studentService; //inject mock into studentServiceImpl

    @Test
    void saveStudent() {
        // given - setup
        StudentDto studentDto = new StudentDto(1001, "arun", 22, "CSE", 4000.25, LocalDate.of(2001, 10, 22), "VIT");

        //when  - action or testing
        Student student=new Student(1001, "arun", 22, "CSE", 4000.25, LocalDate.of(2001, 10, 22), "VIT");
        BDDMockito.when(studentRepository.save(student)).thenReturn(student); //add mock behavior to repository

        StudentDto savedStudentDto = studentService.saveStudent(studentDto); //testing on service

        //then - verify
        Assertions.assertNotNull(savedStudentDto);
        Assertions.assertEquals(studentDto.getId(), savedStudentDto.getId());
        Assertions.assertEquals(studentDto.getName(), savedStudentDto.getName());
        Assertions.assertEquals(studentDto.getAge(), savedStudentDto.getAge());
    }

/*    @Test
    void updateStudent() {
    }

    @Test
    void findStudent() {
    }

    @Test
    void findAllStudents() {
    }

    @Test
    void deleteStudent() {
    }*/
}