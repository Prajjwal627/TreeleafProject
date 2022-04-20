package com.MTM.ManyToMany.controller;




import com.MTM.ManyToMany.entity.Project;
import com.MTM.ManyToMany.exception.ResourceNotFoundException;
import com.MTM.ManyToMany.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping({"/api/v1/project"})

public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProject(){
        return projectRepository.findAll();
    }
    @PostMapping
    public Project createProject(@Valid@RequestBody Project lp) {

        return projectRepository.save(lp);

    }

    @GetMapping("{id}")

    public ResponseEntity<Project> getProjectById(@PathVariable  long id){
        Project lp = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("project not exist with id:" + id));
        return ResponseEntity.ok(lp);
    }

    @PutMapping("{id}")
    public ResponseEntity<Project> updateproject(@PathVariable long id, @Valid@RequestBody Project projectDetails) {
        Project updateproject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("project not exist with id: " + id));

        updateproject.setProjectId(projectDetails.getProjectId());
        updateproject.setTitle(projectDetails.getTitle());


        projectRepository.save(updateproject);

        return ResponseEntity.ok(updateproject);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        projectRepository.delete(project);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    
}
