package org.hibernate_example.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate_example.model.Citizen;
import org.hibernate_example.model.Passport;

public class CitizenService {

    public void create() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Citizen.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Citizen citizen = new Citizen("TestName", 30);
            //Passport passport = new Passport(citizen, 12345);
            Passport passport = new Passport(12345);

            citizen.setPassport(passport);

            session.save(citizen);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }


    public void readByCitizenId(int citizenId) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Citizen.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Citizen citizen = session.get(Citizen.class, citizenId);
            //Passport passport = new Passport(citizen, 12345);
            Passport passport = citizen.getPassport();

            System.out.println("citizen=" + citizen);
            System.out.println("passport=" + passport);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }


    public void readByPassportId(int passportId) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Citizen.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //Passport passport = new Passport(citizen, 12345);
            Passport passport = session.get(Passport.class, passportId);

            Citizen citizen = passport.getCitizen();

            System.out.println("citizen=" + citizen);
            System.out.println("passport=" + passport);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
