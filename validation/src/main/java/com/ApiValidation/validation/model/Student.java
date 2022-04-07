package com.ApiValidation.validation.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

    @Table(name = "students")
    @Entity
    public class Student  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "name", nullable = false)

        //  name should not be null or empty
        //  name should have at least 2 characters
        @NotEmpty
        @Size(min = 2, message = "user name should have at least 2 characters")
        private String name;

        // email should be a valid email format
        // email should not be null or empty
        @NotEmpty
        @NotNull
        @Email
        private String email;

        // password should not be null or empty
        // password should have at least 8 characters
        @NotEmpty
        @NotNull
        @Size(min = 8, message = "password should have at least 8 characters")

        private String password;

        public Student() {

        }

        public Student(String name, String email, String password) {
            super();
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public long getId() {return id;}
        public void setId(long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }

}
