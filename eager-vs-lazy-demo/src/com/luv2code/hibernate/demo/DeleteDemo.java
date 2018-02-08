package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) throws ParseException {
		
		// create session factory ( once in the project)
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		// create a session
		
		Session session = factory.getCurrentSession();
		
		try{
			
			
			// start a transaction
						session.beginTransaction();
			
		// get instructor by id
			int theId = 1;
			Instructor tempInstructor = 
					session.get(Instructor.class, theId);
						
		// delete instructor
			System.out.println("Deleting instructor:  "+tempInstructor);
			session.delete(tempInstructor);
			// will also delete the details object
			
		
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done ! ");
			
		}finally{
			session.close();
			factory.close();
		}
		
		
		
		
		
	}

}
