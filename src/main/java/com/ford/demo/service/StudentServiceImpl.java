package com.ford.demo.service;

import com.ford.demo.dto.StudentDto;
import com.ford.demo.entity.Student;
import com.ford.demo.mapper.StudentMapper;
import com.ford.demo.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    IStudentRepository studentRepository;

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {

        Student student=StudentMapper.convertToStudent(studentDto); //mapping from dto to entity

        Student savedStudent=studentRepository.save(student);

        StudentDto savedStudentDto=StudentMapper.convertToStudentDto(savedStudent); //mapping from entity to dto

        return savedStudentDto;
    }

    @Override
    public StudentDto updateStudent(StudentDto student) {
        return null;
    }

    @Override
    public StudentDto findStudent(int studentId) {

        Optional<Student> optionalStudent=studentRepository.findById(studentId);

        if(optionalStudent.isPresent()){
            StudentDto studentDto=StudentMapper.convertToStudentDto(optionalStudent.get());
            return studentDto;
        }

        return null;
    }

    @Override
    public List<StudentDto> findAllStudents() {
        return List.of();
    }

    @Override
    public boolean deleteStudent(int studentId) {
        return false;
    }
}