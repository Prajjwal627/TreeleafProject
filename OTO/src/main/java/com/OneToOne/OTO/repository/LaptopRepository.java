package com.OneToOne.OTO.repository;



import com.OneToOne.OTO.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>{

}