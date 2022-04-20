package com.MTM.ManyToMany.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @NotEmpty
    @Size(min = 3,message = "title should have more than 3 character")
    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "projects", cascade = { CascadeType.ALL })
    private Set<Student> students = new HashSet<Student>();

    public Project() {
        super();
    }

    public Project(String title) {
        this.title = title;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Project(Long projectId, String title, Set<Student> students) {
        this.projectId = projectId;
        this.title = title;
        this.students = students;
    }
}