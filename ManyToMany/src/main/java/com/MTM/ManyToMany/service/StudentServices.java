package com.MTM.ManyToMany.service;

import com.MTM.ManyToMany.entity.Student;
import com.MTM.ManyToMany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class StudentServices {

    @Autowired
    private StudentRepository repo;

    public List<Student> listAll() {
        return repo.findAll(Sort.by("email").ascending());
    }

}
