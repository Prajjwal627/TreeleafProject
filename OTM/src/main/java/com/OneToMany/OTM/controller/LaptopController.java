package com.OneToMany.OTM.controller;

import com.OneToMany.OTM.entity.Laptop;
import com.OneToMany.OTM.exception.ResourceNotFoundException;
import com.OneToMany.OTM.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping({"/api/v1/laptop"})

public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;

    @GetMapping
    public List<Laptop> getAllLaptop(){
        return laptopRepository.findAll();
    }
    @PostMapping
    public Laptop createLaptop(@RequestBody Laptop lp) {

        return laptopRepository.save(lp);

    }

    @GetMapping("{id}")

    public ResponseEntity<Laptop> getLaptopById(@PathVariable  long id){
        Laptop lp = laptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop not exist with id:" + id));
        return ResponseEntity.ok(lp);
    }


    @PutMapping("{id}")
    public ResponseEntity<Laptop> updateLaptop(@PathVariable long id, @RequestBody Laptop laptopDetails) {
        Laptop updateLaptop = laptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop not exist with id: " + id));

        updateLaptop.setL_id(laptopDetails.getL_id());
        updateLaptop.setL_name(laptopDetails.getL_name());


        laptopRepository.save(updateLaptop);

        return ResponseEntity.ok(updateLaptop);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Laptop laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        laptopRepository.delete(laptop);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
