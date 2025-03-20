package com.ford.demo.repository;

import com.ford.demo.entity.Student;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    IStudentRepository studentRepository;

    @Test
    public void testSaveStudent() {
        // Given - setup
        Student student = new Student(1001, "arun", 22, "CSE", 75000.25, LocalDate.of(2002, 8, 22), "VIT");

        //when -  action(testing..)
        Student actualStudent = studentRepository.save(student);

        //then -what happend (test result)
        Assertions.assertEquals(student.getId(), actualStudent.getId());
        Assertions.assertEquals(student.getName(), actualStudent.getName());
        Assertions.assertEquals(student.getAge(), actualStudent.getAge());
        Assertions.assertEquals(student.getBranch(), actualStudent.getBranch());
    }


    @Test
    public void testGetStudentById() {
        //Given - setup
        Student student = new Student(1001, "arun", 22, "CSE", 75000.25, LocalDate.of(2002, 8, 22), "VIT");
        int studentId = 1001;

        //when - action (testing..)
        studentRepository.save(student); //adding student
        Student actualStudent = studentRepository.findById(studentId).get(); //finding student by passing id

        //then - what happend (test results)
        Assertions.assertEquals(student.getId(), actualStudent.getId());
        Assertions.assertEquals(student.getName(), actualStudent.getName());
        Assertions.assertEquals(student.getAge(), actualStudent.getAge());
        Assertions.assertEquals(student.getBranch(), actualStudent.getBranch());
    }

    @Test
    public void testGetAllStudents() {
        // Given - setup
        Student student1 = new Student(1001, "arun", 22, "CSE", 55000.25, LocalDate.of(2002, 8, 21), "VIT");
        Student student2 = new Student(1002, "varun", 24, "IT", 75000.25, LocalDate.of(2001, 5, 22), "SVIT");
        Student student3 = new Student(1003, "tarun", 26, "IT", 75000.25, LocalDate.of(2001, 7, 11), "SVIT");
        Student student4 = new Student(1004, "charun", 23, "ECE", 65000.25, LocalDate.of(2006, 6, 12), "VIT");
        List<Student> studentList = List.of(student1, student2, student3, student4);
        studentRepository.saveAll(studentList);

        // when -  testing
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<Student> actualStudentList = Lists.newArrayList(studentIterable);

        // then -  verifying results
        Assertions.assertEquals(studentList.size(), actualStudentList.size());
        Assertions.assertEquals(studentList.get(0).getId(), actualStudentList.get(0).getId());
    }



}