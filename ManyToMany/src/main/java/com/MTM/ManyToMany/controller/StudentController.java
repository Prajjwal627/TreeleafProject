package com.MTM.ManyToMany.controller;


import com.MTM.ManyToMany.entity.Student;
import com.MTM.ManyToMany.exception.ResourceNotFoundException;
import com.MTM.ManyToMany.pdfExporter.PDFExporter;
import com.MTM.ManyToMany.pdfExporter.ExcelExporter;
import com.MTM.ManyToMany.repository.StudentRepository;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.MTM.ManyToMany.service.StudentServices;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentServices service;


    @GetMapping
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<Student> listStudents = service.listAll();
        PDFExporter exporter = new PDFExporter(listStudents);
        exporter.export(response);
    }

    @GetMapping("/students/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Student> listStudents = service.listAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Student ID", "E-mail", "First Name", "Last name"};
        String[] nameMapping = {"student_id", "email", "firstName", "lastName"};

        csvWriter.writeHeader(csvHeader);

        for (Student student : listStudents) {
            csvWriter.write(student, nameMapping);
        }

        csvWriter.close();

    }

    @GetMapping("/students/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Student> listStudents = service.listAll();

        ExcelExporter excelExporter = new ExcelExporter(listStudents);

        excelExporter.export(response);
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
