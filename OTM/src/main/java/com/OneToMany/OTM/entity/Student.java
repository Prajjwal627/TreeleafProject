package com.OneToMany.OTM.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Student")
public class Student {
@Id
@Column(name = "s_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int S_id;
    @Column(name = "S_name")
private String S_name;

@OneToMany(mappedBy = "st")
private List<Laptop> laptop;


    public Student(int S_id, String S_name)
    {
        this.S_id = S_id;
        this.S_name = S_name;
    }
    public Student(){

    }

    public int getS_id() {
        return S_id;
    }

    public void setS_id(int S_id) {
        this.S_id = S_id;
    }

    public String getS_name() {
        return S_name;
    }

    public void setS_name(String s_name) {
        S_name = s_name;
    }

}
