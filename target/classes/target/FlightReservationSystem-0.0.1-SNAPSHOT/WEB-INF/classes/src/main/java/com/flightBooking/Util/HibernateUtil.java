package com.flightBooking.Util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.flightBooking.Model.Booking;
import com.flightBooking.Model.Flight;
import com.flightBooking.Model.User;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

//    static {
//        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
    
    static {
        try {
        	
        	EnvLoader.loadEnv();
        	
        	Configuration cfg = new Configuration().configure();
        	cfg.addAnnotatedClass(User.class);
        	cfg.addAnnotatedClass(Flight.class);
        	cfg.addAnnotatedClass(Booking.class);
        	
        	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        	sessionFactory = cfg.buildSessionFactory(reg);
        	
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

