package service;

import dataBaseConnectors.CourseDAO;
import dataBaseConnectors.PersonDAO;
import model.Gender;
import model.Person;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class PersonService {

    private PersonDAO personDAO;
    private CourseDAO courseDAO;

    public PersonService() {
        personDAO = new PersonDAO ();
        courseDAO = new CourseDAO ();
    }

    public void showAllPerson(){
        personDAO.getAllPersons ().forEach (System.out::println);
    }

    public void showPersonById(){
        Scanner scanner = new Scanner(System.in);
        boolean rightInput;
        int id= 0;
        do {
            System.out.println("Insert Id");
            try {
                id = Integer.parseInt(scanner.next());
                scanner.nextLine();
                rightInput= true;
            } catch (NumberFormatException e) {
                rightInput =false;
                System.out.println("Id is not correct");
            }
        }while (!rightInput);

        Person person = personDAO.getPersonById (id);
        if (person!= null) System.out.println(person);
        else System.out.println("This Id does not have a person");
    }


    public void addPerson(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert first name");
        String firstName = scanner.next();

        System.out.println("Insert family name");
        String familyName = scanner.next();

        System.out.println("What do you want to look at? \n1: Men \n2: Women\n3: Xxx");
        boolean exists = false;
        int genderId =0;

        Gender genre = null;

        while (!exists) {
            genderId = makeACorrectId();
            if (genderId == 1) {
                genre = Gender.MEN; exists = true; }
            else if (genderId == 2) {
                genre = Gender.WOMAN; exists = true; }
            else if (genderId == 3) {
                genre = Gender.XXX; exists = true; }
            else System.out.println("This genre doesn't exist.");
        }

        courseDAO.getAllCourses ().forEach (System.out::println);
        System.out.println("Which one of these course do you want to use? Type in a number.");
        int courseId = 0;
        while (!exists) {
            courseId = makeACorrectId();
            if (courseDAO.getCourseById ((long) courseId)!=null)
                exists = true;
            else System.out.println("This course doesn't exist.");
        }


        Person person = new Person (firstName,familyName,genre);

        //insert into table
        personDAO.addPerson (person);
        System.out.println("Person was made");
    }

    public void updatePerson(){
        personDAO.getAllPersons ().forEach (System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        boolean exist =false;
        int currentId = 0;
        while (!exist){
            currentId = makeACorrectId ();
            if (personDAO.getPersonById (currentId) != null) exist=true;
            else System.out.println ("Person doesn't exist");
        }
        Scanner scanner = new Scanner (System.in);
        Person person = personDAO.getPersonById (currentId);
        System.out.println("Do you want to change the first name? NA for nothing");
        String answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String firstName = scanner.next ();
            person.setFirstName (firstName);
        }

        System.out.println("Do you want to change the family name? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String familyName = scanner.next ();
            person.setFamilyName (familyName);
        }

        System.out.println("Do you want to change the family name? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println("What do you want to change it do? \n1: Men \n2: Women\n3: Xxx");
            boolean exists = false;
            int genderId = 0;

            Gender genre = null;

            while (!exists) {
                genderId = makeACorrectId();
                if (genderId == 1) {
                    genre = Gender.MEN; exists = true; }
                else if (genderId == 2) {
                    genre = Gender.WOMAN; exists = true; }
                else if (genderId == 3) {
                    genre = Gender.XXX; exists = true; }
                else System.out.println("This genre doesn't exist.");
            }

            person.setGender (genre);
        }


        personDAO.updatePerson (person);
        System.out.println ("Person updated!");


    }

    public void deleteAPerson() throws SQLException {
        Scanner scanner = new Scanner (System.in);
        personDAO.getAllPersons ().forEach (System.out::println);
        System.out.println ("Give id of person you want to delete:");
        int personId = giveExistingPersonId ();
        System.out.println("Are you sure you want to delete this city:");
        System.out.println(personDAO.getPersonById (personId));
        System.out.println("Y/N");
        String answer = scanner.next ();
        if (answer.toUpperCase(Locale.ROOT).equals ("Y")){
            personDAO.deletePerson (personDAO.getPersonById (personId));
            System.out.println ("Person has been deleted");
        }else System.out.println ("Person has not been deleted");

    }



    private int giveExistingPersonId() throws SQLException {
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (personDAO.getPersonById (currentId)!= null) exist = true;
            else System.out.println("Person doesn't exist");
        }
        return currentId;
    }

    private int makeACorrectId(){
        Scanner scanner = new Scanner(System.in);
        boolean rightInput;
        int id= 0;
        do {
            System.out.println("Insert Id");
            try {
                id = Integer.parseInt(scanner.next());
                scanner.nextLine();
                rightInput= true;
            } catch (NumberFormatException e) {
                rightInput =false;
                System.out.println("Id is not correct");
            }
        }while (!rightInput);
        return id;
    }


}
