package com.example.springassignment1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringAssignment1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAssignment1Application.class, args);
	}
	@GetMapping("/index.html")
	public  String courses() {
		String CSC_department = "Department of Computer Science Offers the following Courses:";
		String F_courses = "Foundation Courses: CSC111F and CSC121F";
		String U_courses = "Undergraduate Courses: CSC113, CSC223, CSC 212, CSC313 and CSC312";
		String H_courses = "Honours Courses: CSC501, CSC512, CSC513 and CSC515";
		return CSC_department+"  "+F_courses +"  "+ U_courses +"  "+ H_courses;
	}
}
