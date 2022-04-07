package com.OneToMany.OTM;


import com.OneToMany.OTM.entity.Laptop;
import com.OneToMany.OTM.entity.Student;
import com.OneToMany.OTM.repository.LaptopRepository;
import com.OneToMany.OTM.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class OtmApplication implements CommandLineRunner {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	LaptopRepository laptopRepository;


	public static void main (String... args){

			SpringApplication.run(OtmApplication.class, args);

		}

		@Override
		public void run (String... args ) throws Exception {


			Student st = new Student();
			st.setS_id(Integer.parseInt("1"));
			st.setS_name("prajjwal Ghimire");

			studentRepository.save(st);

			Laptop lp = new Laptop();
			lp.setL_id(Integer.parseInt("101"));
			lp.setL_name("Lenovo");
			lp.setSt(st);

			Laptop lp2 = new Laptop();
			lp2.setL_id(Integer.parseInt("102"));
			lp2.setL_name("HP");
			lp2.setSt(st);

			laptopRepository.save(lp);
			laptopRepository.save(lp2);
		}
	}


