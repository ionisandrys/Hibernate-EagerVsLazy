package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class GetInstructorAndCoursesDemo {

	public static void main(String[] args) throws ParseException {
		
		// create session factory ( once in the project)
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		// create a session
		
		Session session = factory.getCurrentSession();
		
		try{
			
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db
		int theId=1;
		Instructor tempInstructor = session.get(Instructor.class, theId);
			
		// get instructor
		System.out.println("Instructor: "+ tempInstructor);
			
		// get courses for instructor
		System.out.println("Courses: "+ tempInstructor.getCourses());
			
			
	// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done ! ");
			
		}finally{
			session.close();
			factory.close();
		}
		
		
		
		
		
	}

}
