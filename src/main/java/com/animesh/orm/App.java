package com.animesh.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.animesh.orm.dao.StudentDao;
import com.animesh.orm.entity.Student;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
		
		 // Student student = new Student(1003,"Animesh Manna","Kolkata");
		 // int r = studentDao.insert(student); 
		 // System.out.println("done"+r);
		 

		// Menu Driven console based Application
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		boolean go = true;
		while (go) {
			System.out.println("Press 1 for add student");
			System.out.println("Press 2 for display all student");
			System.out.println("Press 3 for get details of single student");
			System.out.println("Press 4 for delete student");
			System.out.println("Press 5 for update student");
			System.out.println("Press 6 for exit");
			try {
				int input = Integer.parseInt(br.readLine());
				switch (input) {
				case 1:
					// add student
					System.out.println("Enter Student ID");
					int studentId = Integer.parseInt(br.readLine());
					System.out.println("Enter student name");
					String studentName = br.readLine();
					System.out.println("Enter student city");
					String studentCity = br.readLine();
					Student student1 = new Student(studentId, studentName, studentCity);
					int r = studentDao.insert(student1);
					System.out.println("1 Student inserted "+r);
					break;
				case 2:
					// display all student
					List<Student> students = studentDao.getAllStudents();
					for (Student student : students) {
						System.out.println(student.getStudentId() + "\t" + student.getStudentName() + "\t"
								+ student.getStudentCity());
					}
					break;
				case 3:
					// get details of single student
					System.out.println("Enter student ID to get student details");
					int studentId1 = Integer.parseInt(br.readLine());
					Student student2 = studentDao.getStudent(studentId1);
					System.out.println(student2.getStudentId() + "\t" + student2.getStudentName() + "\t"
							+ student2.getStudentCity());
					break;
				case 4:
					// delete student
					System.out.println("Enter student ID to delete student details");
					int studentId2 = Integer.parseInt(br.readLine());
					studentDao.deleteStdent(studentId2);
					System.out.println("student ID : "+studentId2+" has been deleted");
					break;
				case 5:
					// update student
					System.out.println("Enter student ID to update student details");
					int studentId3 = Integer.parseInt(br.readLine());
					System.out.println("Enter student name");
					String studentName3 = br.readLine();
					System.out.println("Enter student city");
					String studentCity3 = br.readLine();
					Student student4 = new Student(studentId3, studentName3, studentCity3);
					studentDao.updateStudent(student4);
					System.out.println("Updated student details is : ");
					Student student5 = studentDao.getStudent(studentId3);
					System.out.println(student5.getStudentId() + "\t" + student5.getStudentName() + "\t"
							+ student5.getStudentCity());
					System.out.println();
					break;
				case 6:
					// exit
					go = false;
					break;
				}

			} catch (Exception e) {
				System.out.println("Invalid input try again !!");
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Thank you for usuing my Application");
	}
}
