package com.MTM.ManyToMany.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    //firstname,lastname and email should not be empty
    @NotEmpty
    @Size(min = 2, message = "first name should have atleast 2 character")
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "lastname should atleast have 2 character")
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @NotEmpty
    @Email
    @Column(name = "email",nullable = false)
    private String email;


    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "student_projects",
            joinColumns = {
                    @JoinColumn(name = "student_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id")
            }
    )
    Set < Project > projects = new HashSet < Project > ();


    public Student() {
        super();
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(Long student_id, String firstName, String lastName, String email, Set<Project> projects) {
        this.student_id = student_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.projects = projects;
    }


    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email;}

    public Set < Project > getProjects() {
        return projects;
    }

    public void setProjects(Set < Project > projects) {
        this.projects = projects;
    }
}