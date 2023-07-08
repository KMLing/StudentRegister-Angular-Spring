package com.ling.backend.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.ling.backend.model.Student;
import com.ling.backend.repository.StudentRepository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	@GetMapping
	public List<Student> getAllStudents(){
		List<Student> studentList=studentRepository.findAll();
		List<Student> students=new ArrayList<>();
		for(Student supDoc:studentList) {

				if(null != supDoc.getLastName() &&  !supDoc.getLastName().equalsIgnoreCase("Ling")) {
					students.add(supDoc);
				}

		}
		System.out.println("new Array list  - "+students.toString());



		if (studentList != null && !studentList.isEmpty()) {
			System.out.println("Before removing doc_submittedapp_form from the list  - "+studentList.toString());

			studentList.removeIf((Student s) ->  null != s.getFirstName()  && s.getFirstName().equalsIgnoreCase("Kaung Myat "));

			System.out.println("After removing doc_submittedapp_form from the list  - "+studentList.toString());

		}

		return studentRepository.findAll();
	}
	@CrossOrigin(origins = "http://localhost:4200/students", maxAge = 3600)
	@PostMapping("/create")
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200/students", maxAge = 3600)
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable long id,@RequestBody Student student){
		Student std=studentRepository.findById(id).get();
		std=student;
		std.setId(id);
		studentRepository.save(std);
		return new ResponseEntity<>(std,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200/students", maxAge = 3600)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable long id){
		studentRepository.deleteById(id);
		return new ResponseEntity<>(id,HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200/students", maxAge = 3600)
	@GetMapping("/student/{id}")
	public ResponseEntity<?>  getStudent(@PathVariable long id){
		Student std=studentRepository.findById(id).get();
		System.out.println("the student "+std.toString());
		return new ResponseEntity<>(std,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200/students", maxAge = 3600)
	@GetMapping("/browserInfo")
	public static String getBrowser(HttpServletRequest request) {
		String LOCALHOST_IPV4 = "127.0.0.1";
		String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
		String ipAddress;
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		ipAddress = inetAddress.getHostAddress();

		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		Browser browser = userAgent.getBrowser();
		String browserName = browser.getName();
		String broEngine =browser.getRenderingEngine().toString();
		String broType=browser.getBrowserType().toString();
		String broGroup=browser.getGroup().toString();
		String broManu=browser.getManufacturer().toString();
	//	String broVersion=browser.getVersion(browserName).toString();

		String bname = browser.name();

		System.out.println("Browser GetName :"+browserName);
		System.out.println("Browser Engine :"+broEngine);
		System.out.println("Browser Type :"+broType);
		System.out.println("Browser Group :"+broGroup);
		System.out.println("Browser Manufacture :"+broManu);
		//System.out.println("Browser broVersion :"+broVersion);
		System.out.println("Browser bname :"+bname);






		return " Internet Address"+ inetAddress +"/ Ip address : "+ipAddress;
	}
	
}
