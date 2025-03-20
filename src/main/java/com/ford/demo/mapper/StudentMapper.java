package com.ford.demo.mapper;

import com.ford.demo.dto.StudentDto;
import com.ford.demo.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    //method that maps  StudentDto  to Student
    public static Student convertToStudent(StudentDto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setBranch(studentDto.getBranch());
        student.setFee(studentDto.getFee());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setCollege(studentDto.getCollege());
        return student;
    }

    //method that maps StudentDtoList  to Student list
    public static  List<Student>  convertToStudentList(List<StudentDto> studentDtoList){
        List<Student> studentList = new ArrayList<>();
        for(StudentDto studentDto : studentDtoList){
            studentList.add(convertToStudent(studentDto));
        }
        return studentList;
    }

    //method that maps StudentList  to StudentDto list
    public static List<StudentDto> convertToStudentDtoList(List<Student> studentList){
        List<StudentDto> studentDtoList = new ArrayList<>();
        for(Student student : studentList){
            studentDtoList.add(convertToStudentDto(student));
        }
        return studentDtoList;
    }

    //method that maps Student to StudentDto
    public static StudentDto convertToStudentDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setBranch(student.getBranch());
        studentDto.setFee(student.getFee());
        studentDto.setDateOfBirth(student.getDateOfBirth());
        studentDto.setCollege(student.getCollege());
        return studentDto;
    }
}