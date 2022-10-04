package org.hibernate_example;

import org.hibernate_example.service.CitizenService;
import org.hibernate_example.service.PersonService;

public class Application {

    private static PersonService personService;
    private static CitizenService citizenService;

    public static void main(String[] args) {

        personService = new PersonService();
        citizenService = new CitizenService();

        /*
        //Read
        Person person = personService.read(1);
        System.out.println(person);

        //ReadAll
        List<Person> personList = personService.readAll();
        System.out.println(personList);

        //ReadAllByAge
        List<Person> personListByAge = personService.readAllByAge(30);
        System.out.println(personListByAge);

        //Create
        Person person1 = new Person("Test1", 30);
        Person person2 = new Person("Test2", 40);
        Person person3 = new Person("Test3", 50);

        personService.create(person1);
        personService.create(person2);
        personService.create(person3);

        //Update
        personService.update(2);

        //Delete
        personService.delete(3);
         */

        /*
        List<Item> items = personService.readItemsOfPerson(3);

        Person owner = personService.readOwnerOfItem(10);

        personService.addItemForOwner(2);

        personService.createNewPersonWithNewItem();

        personService.deleteAllItemsOfPerson(1);
        personService.deletePerson(2);



        personService.changeOwnerForItem(4, 5);


        personService.cascadeMethod();
        */


        citizenService.create();

        citizenService.readByCitizenId(1);
        citizenService.readByPassportId(1);

    }
}
