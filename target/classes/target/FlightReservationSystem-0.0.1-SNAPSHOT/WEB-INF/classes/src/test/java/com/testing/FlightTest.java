package com.testing;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.flightBooking.Model.Flight;

public class FlightTest {
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure().addAnnotatedClass(Flight.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		SessionFactory sf = cfg.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Flight flight = new Flight();
		flight.setFlightNo("IND-456");
        flight.setSource("Delhi");
        flight.setDestination("Kolkata");
        flight.setDepartureTime("14:00");
        flight.setArrivalTime("17:00");
        flight.setSeats(140);
        flight.setPrice(5500.0);
        
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date date = sdf.parse("2025-04-20");
        	flight.setFlightDate(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        session.save(flight);
		
		tx.commit();
		session.close();
		
		System.out.println("Flight added successfully!");
	}

}
