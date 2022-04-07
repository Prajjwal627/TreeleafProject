package com.OneToMany.OTM.entity;

import javax.persistence.*;


@Entity
public class Laptop{
@Id

@Column(name = "L_id")

    private int L_id;
    @Column(name = "L_name")
    private String L_name;


    @ManyToOne
    @JoinColumn(name = "s_id")
    private Student st;

    public Laptop(int l_id, String l_name, Student st) {
        L_id = l_id;
        L_name = l_name;
        this.st = st;
    }
    public Laptop(){

    }

    public int getL_id() {
        return L_id;
    }

    public void setL_id(int l_id) {
        L_id = l_id;
    }

    public String getL_name() {
        return L_name;
    }

    public void setL_name(String l_name) {
        L_name = l_name;
    }

    public Student getSt() {
        return st;
    }

    public void setSt(Student st) {
        this.st = st;
    }

}
