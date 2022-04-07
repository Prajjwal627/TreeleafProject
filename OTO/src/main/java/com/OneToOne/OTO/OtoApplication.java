package com.OneToOne.OTO;


import com.OneToOne.OTO.entity.Laptop;
import com.OneToOne.OTO.entity.Student;
import com.OneToOne.OTO.repository.LaptopRepository;
import com.OneToOne.OTO.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OtoApplication implements CommandLineRunner {
	private final StudentRepository studentRepository;
	private final LaptopRepository laptopRepository;


	public OtoApplication(StudentRepository studentRepository, LaptopRepository laptopRepository) {
		this.studentRepository = studentRepository;
		this.laptopRepository = laptopRepository;
	}

	public static void main (String... args){

		SpringApplication.run(OtoApplication.class, args);

	}

	@Override
	public void run (String... args ) throws Exception {

		Laptop lp = new Laptop();
		lp.setL_id(Integer.parseInt("101"));
		lp.setL_name("Lenovo");
		


		Student st = new Student();
		st.setS_id(Integer.parseInt("1"));
		st.setS_name("prajjwal Ghimire");
		st.setLaptop(lp);



		studentRepository.save(st);
		laptopRepository.save(lp);
	}
}


