package com.ApiValidation.validation.controller;


import javax.validation.Valid;

import com.ApiValidation.validation.Exception.ResourceNotFoundException;
import com.ApiValidation.validation.model.Student;
import com.ApiValidation.validation.repository.StudentRepository;
import com.ApiValidation.validation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    //Using list to get all the student from student repository
    @GetMapping("/students")
    public List<Student> getAllstudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getstudentById(@PathVariable(value = "id") Long StudentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(StudentId)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + StudentId));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student){
        Student savedStudent = studentService.createStudent(student);
        return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                   @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentId));

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setPassword(studentDetails.getPassword());
        final Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentId));

        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

