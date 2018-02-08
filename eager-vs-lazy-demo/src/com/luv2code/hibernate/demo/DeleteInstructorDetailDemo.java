package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

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
			
		// get instructor detail object
			int theId = 3;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
						
						
	// print instructor detail and the associated instructor
			System.out.println("tempInstructorDetail: "+ tempInstructorDetail);
			
			System.out.println("the associated instructor: "+
			tempInstructorDetail.getInstructor());
						
			// delete instructor detail
			
			System.out.println("Deleting tempInstr.Detail "+ tempInstructorDetail);
			
			
			// remove the associated object reference
			// break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			
			session.delete(tempInstructorDetail);			
		
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done ! ");
			
		}catch(Exception e){
			e.printStackTrace(); }
		
		
		finally{
			session.close();
			factory.close();
		}
		
		
		
		
		
	}

}
