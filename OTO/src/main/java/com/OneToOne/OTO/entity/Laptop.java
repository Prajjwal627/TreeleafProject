package com.OneToOne.OTO.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Laptop {
    @Id

    @Column(name = "L_id")
    @NotEmpty
    @Size(min = 1, message = "id should be minimum of 1 character" )
    private int L_id;
    @Column(name = "L_name")
    @NotEmpty
    @Size(min = 2,message = "laptop name cannot be less than 2 character")
    private String L_name;

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

    public Laptop(int l_id, String l_name) {
        L_id = l_id;
        L_name = l_name;
    }

    public Laptop(){

        super();
    }


}

