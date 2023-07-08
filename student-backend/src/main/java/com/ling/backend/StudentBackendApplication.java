package com.ling.backend;

import com.ling.backend.model.Student;
import com.ling.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StudentBackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(StudentBackendApplication.class, args);

	}

}
