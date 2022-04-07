package com.OneToMany.OTM.repository;


import com.OneToMany.OTM.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>{

}