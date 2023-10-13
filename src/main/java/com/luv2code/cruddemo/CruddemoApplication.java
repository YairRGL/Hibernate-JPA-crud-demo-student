package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			//createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students...");
		Long numberRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted rows: " + numberRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		Long id = 1L;
		System.out.println("Deleting student with the id: " + id);
		Student deletedStudent = studentDAO.delete(id);
		System.out.println(deletedStudent.toString() + " has been removed from the database.");
	}

	private void updateStudent(StudentDAO studentDAO) {
		// Retrieve student based on the id
		Long id = 1L;
		Student student = studentDAO.findById(id);
		System.out.println("Retrieving student from database... \n" + student.toString());

		// Update student
		System.out.println("Updating student in the database...");
		student.setFirstName("Scooby");
		studentDAO.update(student);

		System.out.println("Retrieving updated student from database...");
		Student updatedStudent = studentDAO.findById(id);
		System.out.println(updatedStudent.toString());

	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("doe");
		students.forEach(System.out::println);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> list = studentDAO.findAll();
		//list.forEach(l -> System.out.println(l.toString()));
		list.forEach(System.out::println);
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student student = new Student("Juan", "Doe", "juan@test.com");

		System.out.println("Saving student to the database...");
		studentDAO.save(student);

		Long id = student.getId();
		System.out.println("Retrieving the auto-generated id of the new student from the database... \n" + "id:" + id);

		System.out.println("Retrieving this new student from the database...");
		Student newStudent = studentDAO.findById(id);

		System.out.println(newStudent.toString());

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 student objects...");
		Student student1 = new Student("John", "Doe", "john@test.com");
		Student student2 = new Student("Mary", "Doe", "mary@test.com");
		Student student3 = new Student("Ana", "Doe", "ana@test.com");

		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student student = new Student("Juan", "Doe", "john@test.com");

		System.out.println("Saving student to the database...");
		studentDAO.save(student);

		System.out.println("Getting the auto-generated id of the new student from the database... \n" + "id:" + student.getId());
	}

}
