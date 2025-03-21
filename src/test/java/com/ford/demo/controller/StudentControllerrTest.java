package com.ford.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ford.demo.dto.StudentDto;
import com.ford.demo.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerrTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private IStudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addStudentInfo() throws Exception {
        // given - setup
        StudentDto studentDto = new StudentDto(1001, "arun", 22, "CSE", 12000.25, LocalDate.of(2002, 9, 21), "VIT");
        Mockito.when(studentService.saveStudent(studentDto)).thenReturn(studentDto); //mock behavior
        //when  -  testing
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/students/add-student") //making call to rest endpoint
                        .contentType(MediaType.APPLICATION_JSON) //specifying type of data
                        .content(objectMapper.writeValueAsString(studentDto)));  // passing object in string form

        //then -  verifying
        resultActions.andDo(print())
                .andExpect(status().isOk()) //status is success or not
                .andExpect(jsonPath("$.id").value(studentDto.getId()))
                .andExpect(jsonPath("$.name").value(studentDto.getName()))
                .andExpect(jsonPath("$.age").value(studentDto.getAge()));
    }

    @Test
    void getStudentInfo() throws Exception {

        //given  - setup
        StudentDto studentDto = new StudentDto(1001, "arun", 22, "CSE", 12000.25, LocalDate.of(2002, 9, 21), "VIT");
        Mockito.when(studentService.findStudent(studentDto.getId())).thenReturn(studentDto);
        // when -  testing

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/students/find-student").param("sId", String.valueOf(studentDto.getId())).contentType(MediaType.APPLICATION_JSON));

        //then  - verifying
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentDto.getId()))
                .andExpect(jsonPath("$.name").value(studentDto.getName()))
                .andExpect(jsonPath("$.age").value(studentDto.getAge()));
    }

    @Test
    public void testGetAllStudents() throws Exception {
        // given - setup
        StudentDto studentDto1 = new StudentDto(1001, "arun", 22, "CSE", 12000.25, LocalDate.of(2002, 9, 21), "VIT");

        StudentDto studentDto2 = new StudentDto(1002, "varun", 22, "CSE", 12000.25, LocalDate.of(2002, 9, 21), "VIT");

        List<StudentDto> studentDtoList = List.of(studentDto1, studentDto2);

        Mockito.when(studentService.findAllStudents()).thenReturn(studentDtoList);

        //when - testing
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/students/all-students").contentType(MediaType.APPLICATION_JSON));

        //then - verifying
        resultActions.andDo(print())
                .andExpect(status().isOk());
    }

}