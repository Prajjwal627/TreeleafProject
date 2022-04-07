package com.MTM.ManyToMany.controller;


import com.MTM.ManyToMany.entity.Student;
import com.MTM.ManyToMany.exception.ResourceNotFoundException;
import com.MTM.ManyToMany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping({"/api/v1/student"})

public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    @PostMapping
    public Student createStudent(@Valid@RequestBody Student st) {

        return studentRepository.save(st);
    }


    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable  long id){
        Student st = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
        return ResponseEntity.ok(st);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id,@Valid@RequestBody Student studentDetails) {
        Student updateStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));

        updateStudent.setStudent_id(studentDetails.getStudent_id());
        updateStudent.setFirstName(studentDetails.getFirstName());
        updateStudent.setLastName(studentDetails.getLastName());


        studentRepository.save(updateStudent);

        return ResponseEntity.ok(updateStudent);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id){

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));

        studentRepository.delete(student);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }




}
