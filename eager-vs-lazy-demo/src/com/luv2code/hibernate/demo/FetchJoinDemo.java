package com.luv2code.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class FetchJoinDemo {

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
			
			
			// Hibernate query with HQL
			
			// get the instructor from db
		int theId=1;
		
		Query<Instructor> query =
				session.createQuery("select i from Instructor i "
									+ "JOIN FETCH i.courses "
									+ "where i.id=:theInstructorId ", Instructor.class);
		
		// set parameter on the query
		query.setParameter("theInstructorId", theId);
		
		// execute query and get instructor
		
		Instructor tempInstructor = query.getSingleResult();
			
		// get instructor
		System.out.println("Instructor: "+ tempInstructor);
			
		
			// commit transaction
			session.getTransaction().commit();
			
			session.close();
			System.out.println("\nSession is closed. But we use HQL JOIN FETCH for courses before session is closed\n");
			
			System.out.println("Courses: "+ tempInstructor.getCourses());
			
			System.out.println("Done ! ");
			
		}finally{
			
			factory.close();
		}
		
		
		
		
		
	}

}
