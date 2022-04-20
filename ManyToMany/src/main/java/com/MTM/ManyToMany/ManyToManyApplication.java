package com.MTM.ManyToMany;


import com.MTM.ManyToMany.entity.Project;
import com.MTM.ManyToMany.entity.Student;
import com.MTM.ManyToMany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ManyToManyApplication implements CommandLineRunner {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String...args) throws Exception {

		Student student = new Student();
		student.setFirstName("Prajjwal");
		student.setLastName("Ghimire");
		student.setEmail("prajjwal1@gmail.com");

		Student student1 = new Student();
		student1.setFirstName("Renjy");
		student1.setLastName("Maharjan");
		student1.setEmail("renjy1@gmail.com");

		Project project = new Project();
		project.setTitle("Inventory management");

		Project project1 = new Project();
		project1.setTitle("Hotel Mnagamenet Syetem");

		// Add project references in the Student
		student.getProjects().add(project);
		student.getProjects().add(project1);
		student1.getProjects().add(project);
		student1.getProjects().add(project1);

		// Add student reference in the projects
		project.getStudents().add(student);
		project1.getStudents().add(student1);

		project.getStudents().add(student1);
		project1.getStudents().add(student);


		studentRepository.save(student);
	}

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApplication.class, args);
	}
}