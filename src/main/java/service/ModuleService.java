package service;

import dataBaseConnectors.CourseDAO;
import dataBaseConnectors.ModuleDAO;
import model.Course;
import model.Module;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class ModuleService {

    private ModuleDAO moduleDAO;
    private CourseDAO courseDAO;

    public ModuleService() {
        moduleDAO = new ModuleDAO ();
        courseDAO = new CourseDAO ();
    }

    public void showAllModules(){
        moduleDAO.getAllModules ().forEach (System.out::println);
    }

    public void showModuleById(){
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

        Module module = moduleDAO.getModuleById ((long) id);
        if (module!= null) System.out.println (module);
        else System.out.println("This Id does not have a module");

    }

    public void addModule(){
        Scanner scanner = new Scanner(System.in);

        courseDAO.getAllCourses ().forEach (System.out::println);
        System.out.println("Which one of these course do you want to use? Type in a number.");
        boolean exist = false;
        int currentId = 0;
        while (!exist){
            currentId = makeACorrectId();
            if (courseDAO.getCourseById ((long) currentId)!= null) exist = true;
            else System.out.println("Course doesn't exist");
        }
        System.out.println("Insert name");
        String name = scanner.next();
        System.out.println("Insert description");
        String description = scanner.next();

        Module module = new Module (name, description, courseDAO.getCourseById ((long) currentId));
        moduleDAO.addModule (module);
    }

    public void updateModule(){
        moduleDAO.getAllModules ().forEach (System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        boolean exist =false;
        long currentId = 0;
        while (!exist){
            currentId = makeACorrectId ();
            if (moduleDAO.getModuleById ((long) Math.toIntExact (currentId))!= null) exist=true;
            else System.out.println ("Module doesn't exist");
        }
        Scanner scanner = new Scanner (System.in);
        Module module = moduleDAO.getModuleById ((long) Math.toIntExact (currentId));
        System.out.println("Do you want to change the name? NA for nothing");
        String answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String name = scanner.next ();
            module.setName (name);
        }

        System.out.println("Do you want to change the description? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String description = scanner.next ();
            module.setDescription (description);
        }

        System.out.println("Do you want to change the course? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            courseDAO.getAllCourses ().forEach (System.out::println);
            System.out.println("Which one of these course do you want to use? Type in a number.");
            boolean existCourse = false;
            int currentCourseId = 0;
            while (!existCourse){
                currentCourseId = makeACorrectId();
                if (courseDAO.getCourseById ((long) currentCourseId)!= null) existCourse = true;
                else System.out.println("Course doesn't exist");
            }
            module.setCourse (courseDAO.getCourseById ((long) currentCourseId));
        }


        moduleDAO.updateModule (module);
        System.out.println ("Module updated!");
    }

    public void deleteAModule() throws SQLException {
        Scanner scanner = new Scanner (System.in);
        moduleDAO.getAllModules ().forEach (System.out::println);
        System.out.println ("Give id of module you want to delete:");
        int personId = giveExistingPersonId ();
        System.out.println("Are you sure you want to delete this city:");
        System.out.println(moduleDAO.getModuleById ((long) personId));
        System.out.println("Y/N");
        String answer = scanner.next ();
        if (answer.toUpperCase(Locale.ROOT).equals ("Y")){
            moduleDAO.deleteModule (moduleDAO.getModuleById ((long) personId));
            System.out.println ("Module has been deleted");
        }else System.out.println ("Module has not been deleted");
    }




    private int giveExistingPersonId() throws SQLException {
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (moduleDAO.getModuleById ((long) currentId)!= null) exist = true;
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
