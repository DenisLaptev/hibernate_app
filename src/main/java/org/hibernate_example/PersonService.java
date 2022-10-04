package org.hibernate_example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate_example.model.Item;
import org.hibernate_example.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonService {


    public PersonService() {
    }

    public void create(Person person) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            session.save(person);

            session.getTransaction().commit();

            System.out.println("Created person with id=" + person.getId());

        } finally {
            sessionFactory.close();
        }

    }

    public Person read(int id) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person person = null;
        try {
            session.beginTransaction();

            person = session.get(Person.class, 1);
            System.out.println(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return person;
    }

    public List<Person> readAll() {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        List<Person> personList = new ArrayList<>();
        try {
            session.beginTransaction();

            personList = session.createQuery("FROM Person").getResultList();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return personList;
    }

    public List<Person> readAllByAge(int age) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        List<Person> personList = new ArrayList<>();
        try {
            session.beginTransaction();

            personList = session.createQuery("FROM Person WHERE age <= " + age).getResultList();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return personList;
    }


    public Person update(int personId) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person person = null;
        try {
            session.beginTransaction();

//            person = session.get(Person.class, personId);
//            person.setName("New Name");
//            System.out.println(person);

            session.createQuery("UPDATE Person SET name='H-Test' WHERE age > 40").executeUpdate();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return person;

    }

    public void delete(int personId) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person person = null;
        try {
            session.beginTransaction();

//            person = session.get(Person.class, personId);
//            session.delete(person);

            session.createQuery("DELETE FROM Person WHERE age = 30").executeUpdate();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }


    public List<Item> readItemsOfPerson(int personId) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person person = null;
        List<Item> items = null;
        try {
            session.beginTransaction();

            person = session.get(Person.class, personId);
            System.out.println(person);

            items = person.getItems();
            System.out.println(items);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return items;
    }

    public Person readOwnerOfItem(int itemId) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Item item = null;
        Person owner = null;

        try {
            session.beginTransaction();

            item = session.get(Item.class, itemId);
            System.out.println(item);

            owner = item.getOwner();
            System.out.println(owner);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return owner;
    }


    public Person addItemForOwner(int personId) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person owner = null;

        try {
            session.beginTransaction();

            owner = session.get(Person.class, personId);
            System.out.println(owner);

            Item item = new Item("Item from Hibernate", owner);
            System.out.println(item);

            owner.getItems().add(item);

            session.save(item);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return owner;
    }

    public Person createNewPersonWithNewItem() {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person owner = null;
        Item item = null;

        try {
            session.beginTransaction();

            owner = new Person("Test NEW", 100);
            System.out.println(owner);

            item = new Item("Test NEW from Hibernate", owner);
            System.out.println(item);

            owner.setItems(new ArrayList<Item>(Collections.singletonList(item)));

            session.save(owner);
            session.save(item);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return owner;
    }

    public Person deleteAllItemsOfPerson(int personId) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person owner = null;

        try {
            session.beginTransaction();

            owner = session.get(Person.class, personId);
            System.out.println(owner);

            List<Item> items = owner.getItems();

            for (Item item : items) {
                session.remove(item);
            }

            owner.getItems().clear();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
        return owner;
    }

    public void deletePerson(int personId) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person person = null;

        try {
            session.beginTransaction();

            person = session.get(Person.class, personId);

            session.remove(person);

            person.getItems().forEach(item -> item.setOwner(null));

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }


    public void changeOwnerForItem(int itemId, int newOwnerId) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Item item = null;
        Person currentOwner = null;
        Person newOwner = null;

        try {
            session.beginTransaction();

            item = session.get(Item.class, itemId);
            currentOwner = item.getOwner();
            newOwner = session.get(Person.class,newOwnerId);

            //H
            currentOwner.getItems().remove(item);
            //SQL
            item.setOwner(newOwner);
            //H
            newOwner.getItems().add(item);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }


    public void cascadeMethod() {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Person person = null;

        try {
            session.beginTransaction();

            person = new Person("Test cascading",30);
            person.addItem(new Item("Item 1"));
            person.addItem(new Item("Item 2"));
            person.addItem(new Item("Item 3"));

            //session.persist(person);
            session.save(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
