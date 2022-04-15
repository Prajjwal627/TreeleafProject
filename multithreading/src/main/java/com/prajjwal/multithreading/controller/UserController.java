package com.prajjwal.multithreading.controller;


import com.prajjwal.multithreading.model.User;
import com.prajjwal.multithreading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value= "/user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveUsers(@RequestParam("files")MultipartFile[] files) throws Exception {
        for(MultipartFile file:files){
            userService.saveUsers(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping(value = "/user", produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllUsers() {
        return  userService.findAllUsers().thenApply(ResponseEntity::ok);
    }


    @GetMapping(value = "/getUsersByThread", produces = "application/json")
    public  ResponseEntity getUsers(){
        CompletableFuture<List<User>> users1=userService.findAllUsers();
        CompletableFuture<List<User>> users2=userService.findAllUsers();
        CompletableFuture<List<User>> users3=userService.findAllUsers();
        CompletableFuture.allOf(users1,users2,users3).join();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
