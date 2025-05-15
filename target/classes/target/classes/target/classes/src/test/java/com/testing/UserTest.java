package com.testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.flightBooking.Model.User;

public class UserTest {
	
	public static void main(String[] args) {
		User user = new User();
		user.setName("Alice Will");
        user.setEmail("will@example.com");
        user.setPassword("pass123");
        user.setRole("user");
        
        
        Configuration con = new Configuration().configure().addAnnotatedClass(User.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        
        session.save(user);
        
        tx.commit();
        session.close();
        
        System.out.println("User inserted successfully!");
	}

}
