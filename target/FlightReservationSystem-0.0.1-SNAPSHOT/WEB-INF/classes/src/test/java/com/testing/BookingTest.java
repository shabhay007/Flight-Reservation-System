package com.testing;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.flightBooking.Model.Booking;
import com.flightBooking.Model.Booking.BookingStatus;
import com.flightBooking.Model.Flight;
import com.flightBooking.Model.User;

public class BookingTest {
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		cfg.addAnnotatedClass(Booking.class);
		cfg.addAnnotatedClass(User.class);
		cfg.addAnnotatedClass(Flight.class);
		
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		SessionFactory sf = cfg.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		
		// Fetch existing user and flight (assumes they already exist)
        User user = session.get(User.class, 1); // change ID if needed
        Flight flight = session.get(Flight.class, 1); // change ID if needed

        if (user == null || flight == null) {
            System.out.println("User or Flight not found. Insert them first.");
            session.close();
            return;
        }

        Booking booking = new Booking();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2025-04-13");
            booking.setBookingDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        booking.setNumSeats(3);
        booking.setStatus(BookingStatus.BOOKED);
        booking.setUser(user);
        booking.setFlight(flight);
		
		// saving into database
		session.save(booking);
		
		
		tx.commit();
		session.close();
		
		System.out.println("Flight Booked successfully!");
	}

}
