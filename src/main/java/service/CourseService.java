package service;

import dataBaseConnectors.CourseDAO;
import dataBaseConnectors.ModuleDAO;
import model.Course;
import model.Gender;
import model.Person;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class CourseService {
    private CourseDAO courseDAO;
    private ModuleDAO moduleDAO;

    public CourseService() {
        courseDAO = new CourseDAO ();
        moduleDAO = new ModuleDAO ();
    }

    public void showAllCourses(){
        courseDAO.getAllCourses ().forEach (System.out::println);
    }

    public void showCourseById(){
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

        Course course = courseDAO.getCourseById ((long) id);
        if (course!= null) System.out.println (course);
        else System.out.println("This Id does not have a course");
    }

    public void addCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert name");
        String name = scanner.next();

        System.out.println("Insert description");
        String description = scanner.next();

        System.out.println ("Insert code");
        String code = scanner.next ();

        System.out.println ("Insert image URL");
        String imageURL = scanner.next ();

        System.out.println ("Chose a active");
        boolean active = scanner.nextBoolean ();

        Course course = new Course (name,description,code,imageURL,active);

        courseDAO.addCourse (course);
        System.out.println ("Course was made!");

    }

    public void updateCourse(){
        courseDAO.getAllCourses ().forEach (System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        boolean exist =false;
        long currentId = 0;
        while (!exist){
            currentId = makeACorrectId ();
            if (courseDAO.getCourseById ((long) Math.toIntExact (currentId))!= null) exist=true;
            else System.out.println ("Person doesn't exist");
        }
        Scanner scanner = new Scanner (System.in);
        Course course = courseDAO.getCourseById ((long) Math.toIntExact (currentId));
        System.out.println("Do you want to change the name? NA for nothing");
        String answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String firstName = scanner.next ();
            course.setName (firstName);
        }

        System.out.println("Do you want to change the description? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String description = scanner.next ();
            course.setDescription (description);
        }

        System.out.println("Do you want to change the code? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String code = scanner.next ();
            course.setCode (code);
        }

        System.out.println("Do you want to change the image URL? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String imageURL = scanner.next ();
            course.setImageURL (imageURL);
        }

        System.out.println("Do you want to change the family name? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println("What do you want to change it do? \n1: True \n2: False");
            boolean exists = false;
            int activeNumber = 0;
            boolean active = false;

            while (!exists) {
                activeNumber = scanner.nextInt ();
                if (activeNumber == 1) {
                    active = true; exists = true; }
                else if (activeNumber == 2) {
                    active = false; exists = true; }
                else System.out.println("This active status doesn't exist.");
            }

            course.setActive (active);
        }


        courseDAO.updateCourse (course);
        System.out.println ("Course updated!");

    }

    public void deleteACourse() throws SQLException {
        Scanner scanner = new Scanner (System.in);
        courseDAO.getAllCourses ().forEach (System.out::println);
        System.out.println ("Give id of course you want to delete:");
        int personId = giveExistingPersonId ();
        System.out.println("Are you sure you want to delete this city:");
        System.out.println(courseDAO.getCourseById ((long) personId));
        System.out.println("Y/N");
        String answer = scanner.next ();
        if (answer.toUpperCase(Locale.ROOT).equals ("Y")){
            courseDAO.deleteCourse (courseDAO.getCourseById ((long) personId));
            System.out.println ("Course has been deleted");
        }else System.out.println ("Course has not been deleted");
    }

    private int giveExistingPersonId() throws SQLException {
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (courseDAO.getCourseById ((long) currentId)!= null) exist = true;
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
