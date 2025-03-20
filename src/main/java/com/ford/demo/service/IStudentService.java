package com.ford.demo.service;

import com.ford.demo.dto.StudentDto;
import com.ford.demo.entity.Student;

import java.util.List;

public interface IStudentService {

    public StudentDto saveStudent(StudentDto student);
    public StudentDto updateStudent(StudentDto student);
    public StudentDto findStudent(int studentId);
    public List<StudentDto> findAllStudents();
    public boolean deleteStudent(int studentId);
}