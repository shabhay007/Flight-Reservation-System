package com.testing;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flightBooking.Model.User;
import com.flightBooking.Util.HibernateUtil;

public class HibernateTest {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole("user");

        session.save(user);

        tx.commit();
        session.close();

        System.out.println("User inserted successfully!");
    }
}
