package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

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
			// create the objects
			Instructor tempInstructor =
					new Instructor("Barry","Krypkie","barry@caltech.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail("barryK.com/youtube","physics");
			
			// associate the objects
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
						session.beginTransaction();
			
			// save the instructor
			// this will also save the details object due to Cascade.ALL
			session.save(tempInstructor);
			
			
		
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done ! ");
			
		}finally{
			session.close();
			factory.close();
		}
		
		
		
		
		
	}

}
