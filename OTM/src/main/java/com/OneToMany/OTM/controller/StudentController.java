package com.OneToMany.OTM.controller;

import com.OneToMany.OTM.entity.Student;
import com.OneToMany.OTM.exception.ResourceNotFoundException;
import com.OneToMany.OTM.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        public Student createStudent(@RequestBody Student st) {

            return studentRepository.save(st);
        }


        @GetMapping("{id}")
        public ResponseEntity<Student> getStudentById(@PathVariable  long id){
            Student st = studentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
            return ResponseEntity.ok(st);
        }

        @PutMapping("{id}")
        public ResponseEntity<Student> updateStudent(@PathVariable long id,@RequestBody Student studentDetails) {
            Student updateStudent = studentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));

            updateStudent.setS_id(studentDetails.getS_id());
            updateStudent.setS_name(studentDetails.getS_name());


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



